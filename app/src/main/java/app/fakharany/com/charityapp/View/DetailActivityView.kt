package app.fakharany.com.charityapp.View

interface DetailActivityView {
    fun showLastDonation(amount: String)
    fun onLastDonationNotFound()
    fun onFinishedSaveDonation()
    fun onRecieveLastDonation(amount: Double?)
    fun onEditTextEmpty()

}