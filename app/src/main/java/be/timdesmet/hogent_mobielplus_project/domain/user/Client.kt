package be.timdesmet.hogent_mobielplus_project.domain.user

import java.io.Serializable

class Client  : Serializable {
    constructor(id: Int,
                 ownerId: Int,
                 userId: String,
                 uuid: String,
                 firstname: String,
                 lastname: String,
                 fullname: String,
                 companyname: String,
                 email: String,
                 address1: String,
                 address2: String,
                 city: String,
                 fullstate: String,
                 state: String,
                 postcode: String,
                 countrycode: String,
                 country: String,
                 phonenumber: String,
                 tax_id: String,
                 statecode: String,
                 countryname: String,
                 phonecc: String,
                 phonenumberformatted: String,
                 telephoneNumber: String,
                 billingcid: Int,
                 notes: String,
                 currency: Int,
                 defaultgateway: String,
                 groupId: Int,
                 status: String,
                 credit: String,
                 taxexempt: Boolean,
                 latefeeoveride: Boolean,
                 overideduenotices: Boolean,
                 separateinvoices: Boolean,
                 disableautocc: Boolean,
                 emailoptout: Boolean,
                 marketing_emails_opt_in: Boolean,
                 overrideautoclose: Boolean,
                 allowSingleSignOn: Int,
                 email_verified: Boolean,
                 language: String,
                 isOptedInToMarketingEmails: Boolean,
                 lastlogin: String,
                 currency_code: String) {
        this.id = id
        this.ownerId = ownerId
        this.userId = userId
        this.uuid = uuid
        this.firstname = firstname
        this.lastname = lastname
        this.fullname = fullname
        this.companyname = companyname
        this.email = email
        this.address1 = address1
        this.address2 = address2
        this.city = city
        this.fullstate = fullstate
        this.state = state
        this.postcode = postcode
        this.countrycode = countrycode
        this.country = country
        this.phonenumber = phonenumber
        this.tax_id = tax_id
        this.statecode = statecode
        this.countryname = countryname
        this.phonecc = phonecc
        this.phonenumberformatted = phonenumberformatted
        this.telephoneNumber = telephoneNumber
        this.billingcid = billingcid
        this.notes = notes
        this.currency = currency
        this.defaultgateway = defaultgateway
        this.groupId = groupId
        this.status = status
        this.credit = credit
        this.taxexempt = taxexempt
        this.latefeeoveride = latefeeoveride
        this.overideduenotices = overideduenotices
        this.separateinvoices = separateinvoices
        this.disableautocc = disableautocc
        this.emailoptout = emailoptout
        this.marketing_emails_opt_in = marketing_emails_opt_in
        this.overrideautoclose = overrideautoclose
        this.allowSingleSignOn = allowSingleSignOn
        this.email_verified = email_verified
        this.language = language
        this.isOptedInToMarketingEmails = isOptedInToMarketingEmails
        this.lastlogin = lastlogin
        this.currency_code = currency_code
    }

    var id: Int
    var ownerId: Int
    var userId: String
    var uuid: String
    var firstname: String
    var lastname: String
    var fullname: String
    var companyname: String
    var email: String
    var address1: String
    var address2: String
    var city: String
    var fullstate: String
    var state: String
    var postcode: String
    var countrycode: String
    var country: String
    var phonenumber: String
    var tax_id: String
    var statecode: String
    var countryname: String
    var phonecc: String
    var phonenumberformatted: String
    var telephoneNumber: String
    var billingcid: Int
    var notes: String
    var currency: Int
    var defaultgateway: String
    var groupId: Int
    var status: String
    var credit: String
    var taxexempt: Boolean
    var latefeeoveride: Boolean
    var overideduenotices: Boolean
    var separateinvoices: Boolean
    var disableautocc: Boolean
    var emailoptout: Boolean
    var marketing_emails_opt_in: Boolean
    var overrideautoclose: Boolean
    var allowSingleSignOn: Int
    var email_verified: Boolean
    var language: String
    var isOptedInToMarketingEmails: Boolean
    var lastlogin: String
    var currency_code: String
}
