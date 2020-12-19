package be.timdesmet.hogent_mobielplus_project.network.dtos.user

import be.timdesmet.hogent_mobielplus_project.domain.user.Client
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.user.ClientDBO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClientDTO (
    @Json(name = "client_id") val id: Int,
    @Json(name = "owner_user_id") val ownerId: Int,
    @Json(name = "userid") val userId: String,
    @Json(name = "uuid") val uuid: String,
    @Json(name = "firstname") val firstname: String,
    @Json(name = "lastname") val lastname: String,
    @Json(name = "fullname") val fullname: String,
    @Json(name = "companyname") val companyname: String,
    @Json(name = "email") val email: String,
    @Json(name = "address1") val address1: String,
    @Json(name = "address2") val address2: String,
    @Json(name = "city") val city: String,
    @Json(name = "fullstate") val fullstate: String,
    @Json(name = "state") val state: String,
    @Json(name = "postcode") val postcode: String,
    @Json(name = "countrycode") val countrycode: String,
    @Json(name = "country") val country: String,
    @Json(name = "phonenumber") val phonenumber: String,
    @Json(name = "tax_id") val tax_id: String,
    @Json(name = "statecode") val statecode: String,
    @Json(name = "countryname") val countryname: String,
    @Json(name = "phonecc") val phonecc: String,
    @Json(name = "phonenumberformatted") val phonenumberformatted: String,
    @Json(name = "telephoneNumber") val telephoneNumber: String,
    @Json(name = "billingcid") val billingcid: Int,
    @Json(name = "notes") val notes: String,
    @Json(name = "currency") val currency: Int,
    @Json(name = "defaultgateway") val defaultgateway: String,
    @Json(name = "groupid") val groupId: Int,
    @Json(name = "status") val status: String,
    @Json(name = "credit") val credit: String,
    @Json(name = "taxexempt") val taxexempt: Boolean,
    @Json(name = "latefeeoveride") val latefeeoveride: Boolean,
    @Json(name = "overideduenotices") val overideduenotices: Boolean,
    @Json(name = "separateinvoices") val separateinvoices: Boolean,
    @Json(name = "disableautocc") val disableautocc: Boolean,
    @Json(name = "emailoptout") val emailoptout: Boolean,
    @Json(name = "marketing_emails_opt_in") val marketing_emails_opt_in: Boolean,
    @Json(name = "overrideautoclose") val overrideautoclose: Boolean,
    @Json(name = "allowSingleSignOn") val allowSingleSignOn: Int,
    @Json(name = "email_verified") val email_verified: Boolean,
    @Json(name = "language") val language: String,
    @Json(name = "isOptedInToMarketingEmails") val isOptedInToMarketingEmails: Boolean,
    @Json(name = "lastlogin") val lastlogin: String,
    @Json(name = "currency_code") val currency_code: String) {

    fun toDomain() : Client {
        return Client(id, ownerId, userId, uuid, firstname, lastname, fullname, companyname, email, address1, address2, city, fullstate, state, postcode, countrycode, country, phonenumber, tax_id, statecode, countryname, phonecc, phonenumberformatted, telephoneNumber, billingcid, notes, currency, defaultgateway, groupId, status, credit, taxexempt, latefeeoveride, overideduenotices, separateinvoices, disableautocc, emailoptout, marketing_emails_opt_in, overrideautoclose, allowSingleSignOn, email_verified, language, isOptedInToMarketingEmails, lastlogin, currency_code)
    }

    fun toDBO() : ClientDBO {
        return ClientDBO(id, ownerId, userId, uuid, firstname, lastname, fullname, companyname, email, address1, address2, city, fullstate, state, postcode, countrycode, country, phonenumber, tax_id, statecode, countryname, phonecc, phonenumberformatted, telephoneNumber, billingcid, notes, currency, defaultgateway, groupId, status, credit, taxexempt, latefeeoveride, overideduenotices, separateinvoices, disableautocc, emailoptout, marketing_emails_opt_in, overrideautoclose, allowSingleSignOn, email_verified, language, isOptedInToMarketingEmails, lastlogin, currency_code)
    }

}