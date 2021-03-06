form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean = true
  "Did you buy a house in 2010?"
    hasBoughtHouse: string
  "Did you enter a loan?"
    hasMaintLoan: boolean
  "Did you enter a loan?"
      hasMaintLoan: money

  if (hasSoldHouse || (hasMaintLoan == hasBoughtHouse)) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }

}