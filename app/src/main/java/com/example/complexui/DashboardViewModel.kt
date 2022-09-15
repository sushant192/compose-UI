package com.example.complexui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*


sealed interface DashboardUiState {

    var bannerDataList : MutableList<BannerDataClass>
    var sendMoneyDataList : List<imageTextDataClass>
    var contactDataList : List<imageTextDataClass>
    var rechargeDataList : List<imageTextDataClass>
    var graphicsDataList : List<imageTextDataClass>
    var switchAppDataList : List<imageTextDataClass>
    //var colorChange : Long

    data class ToUiState(
        override var bannerDataList : MutableList<BannerDataClass>,
        override var sendMoneyDataList : List<imageTextDataClass>,
        override var contactDataList : List<imageTextDataClass>,
        override var rechargeDataList : List<imageTextDataClass>,
        override var graphicsDataList : List<imageTextDataClass>,
        override var switchAppDataList : List<imageTextDataClass>,
        //override var colorChange : Long
    ) : DashboardUiState
}

private data class DashBoardViewModelState(
    var bannerDataList : MutableList<BannerDataClass>,
    var sendMoneyDataList : List<imageTextDataClass>,
    var contactDataList : List<imageTextDataClass>,
    var rechargeDataList : List<imageTextDataClass>,
    var graphicsDataList : List<imageTextDataClass>,
    var switchAppDataList : List<imageTextDataClass>,
    //var colorChange : Long
    ) {

    fun toUiState(): DashboardUiState = DashboardUiState.ToUiState(
        bannerDataList=bannerDataList,
        sendMoneyDataList=sendMoneyDataList,
        contactDataList =contactDataList,
        rechargeDataList=rechargeDataList,
        graphicsDataList=graphicsDataList,
        switchAppDataList=switchAppDataList,
        //colorChange-colorChange
    )
}


class DashboardViewModel  : ViewModel() {
    private val viewModelState =
        MutableStateFlow(
            DashBoardViewModelState(

                bannerDataList = arrayListOf(),
                sendMoneyDataList = listOf() ,
                contactDataList= listOf(),
                rechargeDataList = listOf(),
                graphicsDataList = listOf(),
                switchAppDataList = listOf(),
                //colorChange= 0xFFD0CBD6

            )
        )

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        getBannerDataList()
        getSendMoneyDataList()
        getContactDataList()
        getRechargeDataList()
        getGraphicsDataList()
        getSwitchAppDataList()
        //changeBackground()


    }

    fun getBannerDataList()
    //:Int
    {
        val bannerData:MutableList<BannerDataClass> = mutableListOf(
            BannerDataClass(0xFFD0CBD6,"Recharge Due","847438393","Rs520","12 sep 2022"),
            BannerDataClass(0xFFD0CBD6,"Biller Due","847438393","Rs520","12 sep 2022"),
            BannerDataClass(0xFFD0CBD6,"DTH Due","847438393","Rs520","12 sep 2022"),
            BannerDataClass(0xFFD0CBD6,"Broadband Due","847438393","Rs520","12 sep 2022")
        )
        viewModelState.update { it.copy(bannerDataList = bannerData) }
        //Log.d("banner list line 60",bannerData.toString())

        //return 5
    }

    fun getSendMoneyDataList(){
        val sendMoneyData:List<imageTextDataClass> =listOf(
            imageTextDataClass("Send Money",R.drawable.scanner,"Scan","Any Code"),
            imageTextDataClass("Send Money",R.drawable.scanner,"UPI ID/","Mobile No."),
            imageTextDataClass("Send Money",R.drawable.scanner,"Bank","Transfer"),
            imageTextDataClass("Send Money",R.drawable.scanner,"Self","Transfer")
        )
        viewModelState.update { it.copy(sendMoneyDataList = sendMoneyData) }
    }

    fun getContactDataList(){
        val contactData:List<imageTextDataClass> =listOf(
            imageTextDataClass("Contacts",R.drawable.profile,"Sushant",""),
            imageTextDataClass("Contacts",R.drawable.profile,"Sushant",""),
            imageTextDataClass("Contacts",R.drawable.profile,"Sushant",""),
            imageTextDataClass("Contacts",R.drawable.arrow,"","")
        )
        viewModelState.update { it.copy(contactDataList = contactData) }
    }

    fun getRechargeDataList(){
        val rechargeData:List<imageTextDataClass> =listOf(
            imageTextDataClass("Recharge & Bill Payments",R.drawable.phone,"Mobile",""),
            imageTextDataClass("Recharge & Bill Payments",R.drawable.phone,"DTH",""),
            imageTextDataClass("Recharge & Bill Payments",R.drawable.phone,"Electricity",""),
            imageTextDataClass("Recharge & Bill Payments",R.drawable.arrow,"","")
        )
        viewModelState.update { it.copy(rechargeDataList =  rechargeData) }
    }

    fun getGraphicsDataList(){
        val graphicsData:List<imageTextDataClass> =listOf(
            imageTextDataClass("Graphics",R.drawable.graphics,"",""),
            imageTextDataClass("Graphics",R.drawable.graphics,"",""),
            imageTextDataClass("Graphics",R.drawable.graphics,"","")
        )
        viewModelState.update { it.copy(graphicsDataList = graphicsData) }
    }

    fun getSwitchAppDataList(){
        val switchAppData:List<imageTextDataClass> =listOf(
            imageTextDataClass("Switch Apps",R.drawable.kfc,"KFC",""),
            imageTextDataClass("Switch Apps",R.drawable.mcd,"MCD",""),
            imageTextDataClass("Switch Apps",R.drawable.pizza,"Domino's",""),
            imageTextDataClass("Switch Apps",R.drawable.arrow,"","")
        )

        viewModelState.update { it.copy(switchAppDataList = switchAppData) }
    }

    fun changeBackground(bannerDataClass: BannerDataClass) {

        bannerDataClass.newColor=0xFFBB86FC
        bannerDataClass.MobileNumber= 9527721680.toString()
        val index=uiState.value.bannerDataList.indexOf(bannerDataClass)

        //uiState.value.bannerDataList[index].newColor=0xFFBB86FC

//        val bannerdata2: ArrayList<BannerDataClass> =arrayListOf(
//            BannerDataClass(0xFFBB86FC,"Biller Due","847438393","Rs520","12 sep 2022"),
//        )
//        bannerdata2
        //val bannerdata3=BannerDataClass(0xFFBB86FC,"Biller Due","847438393","Rs520","12 sep 2022")
        //uiState.value.bannerDataList.set(index,bannerdata3)

//        Log.d("after function call :",
//            bannerDataClass.newColor.toString()
//        )

//        val bannerData1:ArrayList<BannerDataClass> = arrayListOf(
//            //bannerDataClass,
//            BannerDataClass(0xFFBB86FC,"Recharge Due","847438393","Rs520","12 sep 2022"),
//            BannerDataClass(0xFFBB86FC,"Biller Due","847438393","Rs520","12 sep 2022"),
//            BannerDataClass(0xFFBB86FC,"DTH Due","847438393","Rs520","12 sep 2022"),
//            BannerDataClass(0xFFBB86FC,"Broadband Due","847438393","Rs520","12 sep 2022")
//        )

        viewModelState.value.bannerDataList.get(index = index).newColor=0xFFBB86FC

        //val newList:MutableList<BannerDataClass> =uiState.value.bannerDataList
        //newList.set(index = index,bannerDataClass)
//        Log.d("Before function call :",
//            viewModelState.value.bannerDataList.toString()
//        )

        //viewModelState.update { it.copy(bannerDataList=newList) }

//        val newList1:ArrayList<BannerDataClass> =uiState.value.bannerDataList
//
//        Log.d("after function call :",
//            newList1.toString()
//        )
    }
}