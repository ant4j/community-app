#!/usr/bin/perl

use warnings;
use strict;

my $filename = './text/input.txt';

my $checkTitle = 0;
my $titleReady = 0;

open(FH, '<', $filename) or die $!;

my $index = 1;

my $title = "";

my $first = 1;
my $bodyText = "";

while(<FH>) {
		
	if($_ =~ /\d{1,3}\.\ {0,3}([A-Z](\,|\Ì|\È|\À|\Ù|\Ò|\'|\.|\ |\’|\…|\.|\?|\d{1,3})*)+$/) {
		my $currentLine = "$_";
		
		chomp($currentLine);
		
		$title = $currentLine;
		
		$checkTitle = 1;
		$titleReady = 1;
		
		if(!$first) {
			$bodyText =~ s/^\s+|\s+$//g;
			print("status = await client.set(".'"'."cont_text:1".":"."$index".'"'.", ".'`'."$bodyText".'`);'."\n");
			print("console.log(status);"."\n");
			$index++;
			$bodyText = "";
		} else {
			$first = 0;
		}
	} elsif($checkTitle & $_ =~ /([A-Z](\,|\Ì|\È|\À|\Ù|\Ò|\'|\.|\ |\’|\…|\.|\?|\d{1,3})*)+$/) {
		my $currentLine = "$_";
		
		chomp($currentLine);
		
		$currentLine =~ s/^\s+//;
		
		$title = "$title"." $currentLine";
		
		$checkTitle = 0;
	} else {
		if($checkTitle) {
			$checkTitle = 0;
		}
		
		if($titleReady) {			
			print("status = await client.sAdd(".'"'."cont".'"'.", ".'"{ \"id\": \"'."$index".'\", \"coll_id\": \"1\", \"title\": \"'."$title".'\" }");'."\n");
			print("console.log(status);"."\n");
			$titleReady = 0;
		}
		
		my $currentLine = "$_";

		$currentLine =~ s/\"/\\"/g;
		
		$bodyText = "$bodyText".$currentLine;
	}
}

$bodyText =~ s/^\s+|\s+$//g;
print("status = await client.set(".'"'."cont_text:1".":"."$index".'"'.", ".'`'."$bodyText".'`);'."\n");
print("console.log(status);"."\n");

print("status = await client.set(".'"cont_id_seq"'.", ".'"'."$index".'");'."\n");
print("console.log(status);"."\n");

close(FH);
