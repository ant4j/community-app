function main(args) {

  let idCollection = args.idCollection

  console.log(idCollection)

  return {
    "body": [
      { id: "1", name: "Cantico 1" },
      { id: "2", name: "Cantico 2" }
    ]
  }
}
exports.main = main