//Q=4
form taxOfficeExample {
  "What was the selling price?"
    sellingPrice: decimal = 1.5

  "How many days did you wait?"
    waitDays: integer = 5

    if (sellingPrice * waitDays > 5) {
      "Did you wait to long?"
        waitedToLong: boolean = true

            if (sellingPrice * waitDays > 6) {
              "Did you wait to long2?"
                waitedToLong2: boolean = true
            }
    }

}