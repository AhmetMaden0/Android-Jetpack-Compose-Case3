package com.example.enerjisacase3

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetayScreen(navController: NavController){
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

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                StepperExample(currentStep = 3)
            }

            item {
                Spacer(modifier = Modifier.height(5.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Arıza Detayı",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF486072),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = " (isteğe bağlı)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                }
            }

            item {
                var aciklama by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = aciklama,
                        onValueChange = { newValue ->
                            if (newValue.length <= 500) aciklama = newValue
                        },
                        placeholder = {
                            Text(
                                text = "Açıklama giriniz",
                                color = Color(0xFFA9B3BB)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        maxLines = 6,
                        singleLine = false,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${aciklama.length} / 500",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp, end = 16.dp)
                ) {
                    // Başlık
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Arıza Fotoğrafı",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(0xFF486072)
                        )
                        Text(
                            text = " (isteğe bağlı)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Alt açıklama
                    Text(
                        text = "Yükleyeceğiniz fotoğraflar jpeg, png formatında olmalıdır.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color =  Color(0xFF486072)
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    // Fotoğraf yükleme alanı
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start=16.dp, end=16.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp), // kutular arası boşluk
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(85.dp) // Kare boyutu
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                                    .clickable {
                                        // Fotoğraf seçme fonksiyonu
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(Color(0xFFFFA300), shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "+",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }


            item {
                var phoneNumber by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 15.dp) // başlık ve TextField arası padding
                ) {
                    Text(
                        text = "Kişisel Bilgiler",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF486072),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Cep Telefonu",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = Color(0xFF486072),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        placeholder = {
                            Text(
                                text = "Ör: 5xx - xxx - xx - xx",
                                color = Color(0xFFA9B3BB)
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                var selectedOption by remember { mutableStateOf("sms") }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    // Başlık
                    Text(
                        text = "Geri Bildirim Seçenekleri",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF486072),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    // Kutu
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .padding(12.dp)
                    ) {
                        Column {
                            // Seçenek 1
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedOption == "none",
                                    onClick = { selectedOption = "none" },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFFEEA540),
                                        unselectedColor = Color.LightGray
                                    )
                                )
                                Text(
                                    text = "Geri Bildirim İstemiyorum",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W500,
                                    color = Color(0xFF486072)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Seçenek 2
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedOption == "sms",
                                    onClick = { selectedOption = "sms" },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFFEEA540),
                                        unselectedColor = Color.LightGray
                                    )
                                )
                                Text(
                                    text = "Sms ile Geri Bildirim Gönder",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W500,
                                    color = Color(0xFF486072)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Açıklama metni
                            Text(
                                text = "Geri Bildirim kayıtlı telefon numarasına SMS ile gönderilecektir.",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0xFF6C8193),
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }




            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Sol buton (1/3)
                    Button(
                        onClick = { navController.navigate(Screen.Adres.route) },
                        modifier = Modifier
                            .weight(1.2f)
                            .height(55.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFFFCEE50), Color(0xFFEEA540))
                            )
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Geri Dön",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color(0xFFFCEE50), Color(0xFFEEA540))
                                    )
                                )
                            )
                        }
                    }

                    // Sağ buton (2/3)
                    Button(
                        onClick = { navController.navigate(Screen.Son.route) },
                        modifier = Modifier
                            .weight(1.5f)
                            .height(55.dp),
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
                                        colors = listOf(Color(0xFFFCEE50), Color(0xFFEEA540))
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Başvuruyu Tamamla",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp)) // alt boşluk
            }
        }
    }
}