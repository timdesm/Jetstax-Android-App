package be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.timdesmet.hogent_mobielplus_project.domain.user.Client

@Entity(tableName = "clients")
class ClientDBO constructor(
    @PrimaryKey
    var id: Int,
    var ownerId: Int,
    var userId: String,
    var uuid: String,
    var firstname: String,
    var lastname: String,
    var fullname: String,
    var companyname: String,
    var email: String,
    var address1: String,
    var address2: String,
    var city: String,
    var fullstate: String,
    var state: String,
    var postcode: String,
    var countrycode: String,
    var country: String,
    var phonenumber: String,
    var tax_id: String,
    var statecode: String,
    var countryname: String,
    var phonecc: String,
    var phonenumberformatted: String,
    var telephoneNumber: String,
    var billingcid: Int,
    var notes: String,
    var currency: Int,
    var defaultgateway: String,
    var groupId: Int,
    var status: String,
    var credit: String,
    var taxexempt: Boolean,
    var latefeeoveride: Boolean,
    var overideduenotices: Boolean,
    var separateinvoices: Boolean,
    var disableautocc: Boolean,
    var emailoptout: Boolean,
    var marketing_emails_opt_in: Boolean,
    var overrideautoclose: Boolean,
    var allowSingleSignOn: Int,
    var email_verified: Boolean,
    var language: String,
    var isOptedInToMarketingEmails: Boolean,
    var lastlogin: String,
    var currency_code: String) {

    fun toDomain() : Client {
        return Client(id, ownerId, userId, uuid, firstname, lastname, fullname, companyname, email, address1, address2, city, fullstate, state, postcode, countrycode, country, phonenumber, tax_id, statecode, countryname, phonecc, phonenumberformatted, telephoneNumber, billingcid, notes, currency, defaultgateway, groupId, status, credit, taxexempt, latefeeoveride, overideduenotices, separateinvoices, disableautocc, emailoptout, marketing_emails_opt_in, overrideautoclose, allowSingleSignOn, email_verified, language, isOptedInToMarketingEmails, lastlogin, currency_code)
    }
}