package com.example.complexui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.complexui.ui.theme.ComplexUITheme

class MainActivity : ComponentActivity() {

    private val viewModel: DashboardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //Log.d("before function call :","")
        //val data=viewModel.getBannerDataList()
        //Log.d("after function call :",data.toString())

        val colorViewModel: ColorChangeViewModel by viewModels()
        
        setContent {
            MyApp(viewModel,colorViewModel)
        }
    }
}


@Composable
fun MyApp(viewModel: DashboardViewModel, colorViewModel: ColorChangeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Log.d("banner list line 52",uiState.bannerDataList.toString())
    //Text(text = "Banner data list :"+uiState.bannerDataList.toString())

    LazyColumn() {
        item(1){
            Column {
                //Text(text = viewModel.uiState.toString())
                //CardAlerts(uiState,colorViewModel)
                CardAlerts(uiState,viewModel)
                imageTextUi(uiState.sendMoneyDataList)
                approveUpiRequests()
                UPI()
                imageTextUi(uiState.contactDataList)
                imageTextUi(uiState.rechargeDataList)
                graphics(uiState)
                imageTextUi(uiState.switchAppDataList)
            }
        }
    }
}

@Composable
fun CardAlerts(uiState: DashboardUiState, dashBoardViewModel: DashboardViewModel) {
    LazyRow(modifier = Modifier.padding(8.dp)){
        items(uiState.bannerDataList){
            Card(
                shape = RoundedCornerShape(30.dp),
                //backgroundColor = Color(0xFFD0CBD6),
                //backgroundColor = Color(colorViewModel.colorChange.value),
                backgroundColor = Color(it.newColor),
                modifier = Modifier
                    .padding(6.dp)
                    .width(370.dp)
                    .height(270.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(modifier=Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = it.CardTitle,
//                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight(900),
                            fontSize = 30.sp
                        )
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    }
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        Column(
                            modifier = Modifier.fillMaxHeight(0.5f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.logo),
                                contentDescription = "avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
//                                    .border(0.dp, Color.Gray, CircleShape)
                            )
                        }
                        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                            Text(
                                text = it.MobileNumber,
                                fontSize = 18.sp,
                                fontWeight = FontWeight(600),
                            )
                            Text(
                                text = it.PreviousPlan,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                    Text(
                        text = it.DueDate,
                        modifier = Modifier.padding(top = 5.dp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight(500)
                    )

                    OutlinedButton(
                        //onClick = {colorViewModel.changeBackground(0xFFBB86FC)},
                        onClick = {dashBoardViewModel.changeBackground()},
                        border = BorderStroke(0.dp, Color.Blue),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            backgroundColor = Color.Blue
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .height(65.dp)
                    ) {
                        Text(
                            text = "Pay now & save ₹10",
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500)
                        )
                    }

                }
            }
        }

    }
}

@Composable
fun imageTextUi(uiState: List<imageTextDataClass>){
    //var list=validate(number,uiState)
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text =uiState[0].title,
            fontWeight = FontWeight(900),
            fontSize = 30.sp,
            modifier = Modifier.padding(10.dp)
        )
        LazyRow(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        )
        {
            items(uiState){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        //painter = painterResource(R.drawable.scanner),
                        painter = painterResource(it.imageUrl),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Gray, CircleShape)
                    )
                    Text(text = it.imageTextMain,fontSize = 15.sp, fontWeight = FontWeight(500), textAlign = TextAlign.Center)
                    Text(text = it.imageTextSecondary,fontSize = 15.sp, fontWeight = FontWeight(500))
                }
            }
        }
    }
}

@Composable
fun approveUpiRequests () {
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Blue,
            backgroundColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
    ) {
        Text(
            text = "Approve Pending UPI requests(3) >",
            fontSize = 14.sp,
            fontWeight = FontWeight(500)
        )
    }
}

@Composable
fun UPI(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .padding(vertical = 10.dp)) {
        Column(
            modifier = Modifier.fillMaxHeight(0.5f),
//            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.upi),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
//                    .clip(CircleShape)
                    .border(0.dp, Color.Gray, CircleShape)
                    .padding(horizontal = 5.dp)
            )
        }
        Column (modifier= Modifier
            .weight(1f)
            .padding(horizontal = 20.dp), verticalArrangement = Arrangement.Center){
            Text(
                text = "My UPI Id : 9527721680@jio",
                fontSize = 15.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text = "Check Balance",
                fontSize = 15.sp,
                color = Color.Blue,
                modifier = Modifier.padding(vertical = 4.dp),
                fontWeight = FontWeight(500)
            )
        }
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        }
    }
}

@Composable
fun graphics(uiState: DashboardUiState){
    LazyRow(modifier = Modifier.padding(20.dp)){
        items(uiState.graphicsDataList){
//            Card(
//                shape = RoundedCornerShape(10.dp),
//                backgroundColor = Color.Gray,
//                modifier = Modifier
//                    .padding(10.dp)
//                    .width(350.dp)
//            ) {
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {

                    Image(
                        painter = painterResource(it.imageUrl),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(300.dp)
//                            .clip(CircleShape)
                            .border(1.dp, Color.Gray, CircleShape)
                            .fillMaxWidth(1f)
                    )
                }
//            }
        }

    }
}



fun validate(number: Int,uiState: DashboardUiState): List<imageTextDataClass> {
    if(number==1){
         return uiState.sendMoneyDataList
    }
    else if(number==2){
        return uiState.contactDataList
    }
    else if(number==3){
        return uiState.rechargeDataList
    }
    return uiState.switchAppDataList
}

//@Composable
//fun switchApps(uiState: DashboardUiState){
//    Column (modifier = Modifier
//        .padding(top = 15.dp)
//        .padding(bottom = 15.dp)
//        .padding(3.dp)
//    ){
//        Text(text = "Switch Apps",
//            fontWeight = FontWeight(900),
//            fontSize = 30.sp,
//            modifier = Modifier.padding(10.dp)
//        )
//        LazyRow(
//            modifier = Modifier
//                .padding(5.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//            items(uiState.switchAppDataList){
//                Column() {
//                    Image(
//                        painter = painterResource(it.imageUrl),
//                        contentDescription = "avatar",
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .size(35.dp)
//                            .clip(CircleShape)
//                            .border(0.dp, Color.Gray, CircleShape)
//                    )
//                    Text(text = it.imageTextMain,fontSize = 15.sp)
//                }
//            }
//        }
//    }
//}


@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    ComplexUITheme {
        //switchApps()
    }
}