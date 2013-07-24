private String encodeWiegand(Integer facilityCode, Integer cardNumber) {
            
    // get the binary representation of the facility and card codes
    String binaryFacilityCode = Integer.toBinaryString(facilityCode)
    String binaryCardNumber = Integer.toBinaryString(cardNumber)
    // Build the binary string for the card code
    String rawCardNumber  = "${binaryFacilityCode}${binaryCardNumber}"
    // calculate the left 13 digit parity check
    boolean leftParity = false
    rawCardNumber[0..12].each{ if (it == "1") { leftParity = ! leftParity } }
    String l = leftParity ? '1' : '0'
    // calculate the right 13 digit parity check
    boolean rightParity = true
    rawCardNumber[13..23].each{ if (it == "1") { rightParity = ! rightParity } }
    String r = rightParity ? '1' : '0'
    // build the binary string w/ the parity bits
    String binaryData = "${l}${rawCardNumber}${r}"
    BigInteger cardData = Integer.parseInt(binaryData, 2)
    // return the hex version
    return cardData.toString(16).toUpperCase()
}

// Print out a bunch of code pairs
(41210..41709).each{
    println "${it}: ${encodeWiegand(178, it)}"
}

