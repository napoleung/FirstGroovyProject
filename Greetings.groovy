package firstgroovyproject
/* it is just demo the parsing of xml, for a trade request
also json for limit enquiry response
*/

import groovy.json.JsonSlurper


class Greetings {
	static void main(def args) {
		//def mygreeting = "Hello World"
		//println mygreeting
//        def xmlsluper = new groovy.util.XmlSlurper()
//        def xml = xmlsluper.parseText(new File("D:/dev/FristGroovyProject/TrdRequest.xml").text)
        def xml = new groovy.util.XmlSlurper().parseText(new File("D:/dev/FristGroovyProject/TrdRequest.xml").text)

        def Cpty = xml.PRIMARY.Cpty.Cpty_ShortName.text()
        def Amount = Float.parseFloat(xml.INQUIRY.SpotDeals.Amount1.text())
        println "Amount:" + Amount
        println ('Cpty:' + Cpty)
        def x = (Amount<200) ? 'ok': 'fail'
        println x

		def slurper = new groovy.json.JsonSlurper()
//		def result = slurper.parseText('{"Banking":{"ID": 1, "name":"Guillaume","limit":40000,"account":["saving","credit card"]}}')
        def result = slurper.parseText(new File("D:/dev/FristGroovyProject/LimitsForCpty.json").text)
//        println 'User ID: ' + result.Banking.ID
//        println 'User ShortName: ' + result.Banking.name
//        println 'Limit: ' + result.Banking.limit
//        println 'Account' + result.Banking.account
        def cpty = 'BSG_SPOT'
        def fLimit = 0.0
        result.each {
            if ((it.name) == cpty) {
                    fLimit = it.limit as Float
                    println 'fLimit' + fLimit
                    println it.name + ": " + it.limit
	            }
        }
    }
}
