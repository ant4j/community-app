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
			print("await client.db(\"pool\").collection(\"cont_text\").insertOne({cont_id:$index,coll_id:1,text:`$bodyText`});\n");
			print("console.log(\"OK\");\n");
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
			print("await client.db(\"pool\").collection(\"cont\").insertOne({id:$index,coll_id:1,title:\"$title\"});\n");
			print("console.log(\"OK\");\n");
			$titleReady = 0;
		}
		
		my $currentLine = "$_";

		$currentLine =~ s/\"/\\"/g;
		
		$bodyText = "$bodyText".$currentLine;
	}
}

$bodyText =~ s/^\s+|\s+$//g;
print("await client.db(\"pool\").collection(\"cont_text\").insertOne({cont_id:$index,coll_id:1,text:`$bodyText`});\n");
print("console.log(\"OK\");\n");

print("await client.db(\"pool\").collection(\"sequence\").insertOne({id:\"cont_id_seq\",seq_value:$index});\n");
print("console.log(\"OK\");\n");

close(FH);
