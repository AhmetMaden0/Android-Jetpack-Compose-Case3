package com.example.enerjisacase3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.enerjisacase3.ui.theme.EnerjisaCase3Theme
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.buildAnnotatedString
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import android.util.Log
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment.Companion as Alignment1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnerjisaCase3Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.ArızaTipi.route) {
                    composable(Screen.ArızaTipi.route) {
                        Arıza(navController) // NavController parametresi
                    }
                    composable(Screen.Adres.route) {
                        AdresScreen(navController)
                    }
                    composable(Screen.Detaylar.route) {
                        DetayScreen(navController)
                    }
                    composable(Screen.Son.route) {
                        SonScreen(navController)
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Arıza(navController: NavController) {
    var secilen by remember { mutableStateOf("Bölgede elektrik yok") }
    var showWarning by remember { mutableStateOf(true) }
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Arıza Bildir",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            lineHeight = 22.sp
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFCEE50),
                                Color(0xFFEEA540)
                            )
                        )
                    ),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    navController.navigate(Screen.Adres.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(start = 16.dp, end = 16.dp, bottom = 15.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFCEE50),
                                    Color(0xFFEEA540)
                                )
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Devam et",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {

            item {
                StepperExample(currentStep = 1)
            }

            item {
                Spacer(modifier = Modifier.height(5.dp))
            }

            item{
                Text(
                    text = "Arıza Tipi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF486072),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp),
                    textAlign = TextAlign.Start
                )

            }

            item {
                val arizaTipleri = listOf(
                    "Bölgede elektrik yok",
                    "Evimde elektrik yok",
                    "Sokak Lambası Arızası",
                    "Direk /Trafo Hasarı, Hat Kablo Tel Kopması, Yangın",
                    "Aydınlatma Tehlikeli Durum"
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    arizaTipleri.forEachIndexed { index, tip ->
                        Row(
                            modifier = Modifier
                                .width(350.dp)
                                .height(50.dp)
                                .background(
                                    color = if (selectedIndex == index) Color(0xFFEAF3FF) else Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .border(
                                    width = if (selectedIndex == index) 2.dp else 1.dp,
                                    color = if (selectedIndex == index) Color(0xFF3399FF) else Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    selectedIndex = index
                                    secilen = tip
                                    showWarning = true // her seçimde uyarı aç
                                }
                                .padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.info),
                                contentDescription = "Bilgi İkonu",
                                modifier = Modifier.size(20.dp),
                                tint = Color(0xFFF3BC45)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = tip,
                                fontSize = 15.sp,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )

                            RadioButton(
                                selected = selectedIndex == index,
                                onClick = {
                                    selectedIndex = index
                                    secilen = tip
                                    showWarning = true // radio buttona tıklayınca da uyarı aç
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFFFF6600),
                                    unselectedColor = Color.LightGray
                                )
                            )
                        }
                    }
                }

                //Direk-Trafo Hasarı
                if (secilen == "Direk /Trafo Hasarı, Hat Kablo Tel Kopması, Yangın" && showWarning) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Direk / Trafo Hasarı, Hat Kablo Tel Kopması, Yangın",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Direk / Trafo Hasarı, Hat Kablo Tel Kopması, Yangın, Elektrik direğinden kıvılcım çıkması, direğin veya devrilmesi, direkte elektrik kaçağı olması gibi tehlikeli durumu içeren bir şikayetiniz varsa talebinizi bu kategoriden açabilirsiniz.",
                                        color = Color(0xFF6C8193),
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            IconButton(
                                onClick = { showWarning = false },
                                modifier = Modifier
                                    .size(36.dp)
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Kapat",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }

                //Bölgede elektrik yok
                if (secilen == "Bölgede elektrik yok" && showWarning) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Bölgede elektrik yok",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Bölgenizde planlı bakım, arıza veya beklenmedik bir kesinti nedeniyle elektrik hizmeti verilememektedir. Kesinti süresi ve çalışmalar hakkında detaylı bilgi almak için müşteri hizmetlerimizi arayabilir veya mobil uygulamamız üzerinden güncel durumu takip edebilirsiniz",
                                        color = Color(0xFF6C8193),
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            IconButton(
                                onClick = { showWarning = false },
                                modifier = Modifier
                                    .size(36.dp)
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Kapat",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }

                //Evimde elektrik yok
                if (secilen == "Evimde elektrik yok" && showWarning) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Evimde elektrik yok",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Evinizde elektrik hizmeti verilememektedir. Bu durum planlı bakım, arıza veya beklenmedik bir kesinti nedeniyle oluşmuş olabilir. Kesintinin tahmini süresi ve nedeni hakkında bilgi almak için müşteri hizmetlerimizi arayabilir veya mobil uygulamamız üzerinden güncel durumu takip edebilirsiniz.",
                                        color = Color(0xFF6C8193),
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            IconButton(
                                onClick = { showWarning = false },
                                modifier = Modifier
                                    .size(36.dp)
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Kapat",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }

                //Sokak lambası arızası
                if (secilen == "Sokak Lambası Arızası" && showWarning) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Sokak Lambası Arızası",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Sokak aydınlatma lambası yanmıyor, sürekli yanıp sönüyor veya hasar görmüşse bu kategoriden bildirim yapabilirsiniz.",
                                        color = Color(0xFF6C8193),
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            IconButton(
                                onClick = { showWarning = false },
                                modifier = Modifier
                                    .size(36.dp)
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Kapat",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }

                //Aydınlatma Tehlikeli Durum
                if (secilen == "Aydınlatma Tehlikeli Durum" && showWarning) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Aydınlatma Tehlikeli Durum",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Aydınlatma direğinde kablo sarkması, elektrik kaçağı, devrilme riski veya kıvılcım çıkması gibi can ve mal güvenliğini tehdit eden durumlarda bu kategoriden bildirim yapabilirsiniz",
                                        color = Color(0xFF6C8193),
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            IconButton(
                                onClick = { showWarning = false },
                                modifier = Modifier
                                    .size(36.dp)
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Kapat",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object ArızaTipi : Screen("arıza")
    object Adres : Screen("adres")
    object Detaylar : Screen("detay")
    object Son : Screen("son")
}



//STEPPER
@Composable
fun StepperExample(currentStep: Int) {
    val steps = listOf("Arıza Tipi", "Adres", "Detaylar")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment1.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        steps.forEachIndexed { index, title ->
            Column(
                horizontalAlignment = Alignment1.CenterHorizontally
            ) {
                // Daire
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            if (index + 1 <= currentStep) Color(0xFFFFA500) // Turuncu
                            else Color.LightGray
                        ),
                    contentAlignment = Alignment1.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Başlık
                Text(
                    text = title,
                    color = if (index + 1 <= currentStep) Color(0xFF486072) else Color.Gray,
                    fontSize = 12.sp
                )
            }

            // Adımlar arası çizgi
            if (index != steps.lastIndex) {
                Spacer(modifier = Modifier.width(8.dp))
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .align(Alignment1.CenterVertically),
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}


