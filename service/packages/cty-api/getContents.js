function main(args) {

  let idCollection = args.idCollection

  console.log(idCollection)

  return {
    "body": [
      { id: "1", name: "Albachiara" },
      { id: "2", name: "Laura non c'è" }
    ]
  }
}
exports.main = main