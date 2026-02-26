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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdresScreen(navController: NavController){
    var selected by remember { mutableStateOf("Konumumu Kullan") }
    var selectedIndex by remember { mutableStateOf(0) }

    // Popup kontrolü
    var showDialog by remember { mutableStateOf(false) }

    // ---- İl ----
    val iller = listOf("İl seçiniz", "İstanbul", "Ankara", "İzmir")
    var ilExpanded by remember { mutableStateOf(false) }
    var selectedIl by remember { mutableStateOf(iller[0]) }

    // ---- İlçe ----
    val ilceler = listOf("İlçe seçiniz", "Kadıköy", "Çankaya", "Konak")
    var ilceExpanded by remember { mutableStateOf(false) }
    var selectedIlce by remember { mutableStateOf(ilceler[0]) }

    // ---- Bucak ----
    val bucaklar = listOf("Bucak seçiniz", "Merkez Bucak", "Kuzey Bucak", "Güney Bucak")
    var bucakExpanded by remember { mutableStateOf(false) }
    var selectedBucak by remember { mutableStateOf(bucaklar[0]) }

    // ---- Belde ----
    val beldeler = listOf("Belde seçiniz", "Yeniköy", "Bahçelievler", "Karşıyaka")
    var beldeExpanded by remember { mutableStateOf(false) }
    var selectedBelde by remember { mutableStateOf(beldeler[0]) }

    //mahalle
    val mahalleler=listOf("Mahalle seçiniz", "Etiler", "Ulus", "Atatürk")
    var mahalleExpanded by remember { mutableStateOf(false) }
    var selectedMahalle by remember { mutableStateOf(beldeler[0]) }

    //sokak
    val sokaklar=listOf("Sokak seçiniz", "Yeşil", "Meclis", "Çınar")
    var sokakExpanded by remember { mutableStateOf(false) }
    var selectedSokak by remember { mutableStateOf(beldeler[0]) }

    //Bina
    var selectedBina by remember { mutableStateOf("") }

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
                .fillMaxWidth()
        ) {
            item {
                StepperExample(currentStep = 2)
            }

            item{
                Text(
                    text = "Adres Tipi",
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
                Spacer(modifier = Modifier.height(5.dp))
            }

            item {
                val konumTipleri = listOf(
                    "Farklı bir adres",
                    "Konumumu Kullan"
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top=5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    konumTipleri.forEachIndexed { index, tip ->
                        Row(
                            modifier = Modifier
                                .width(175.dp)
                                .height(50.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .border(
                                    width = 3 .dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = tip,
                                fontSize = 14.sp,
                                color = Color(0xFF486072),
                            )

                            RadioButton(
                                selected = selectedIndex == index,
                                onClick = {
                                    selectedIndex = index
                                    selected = tip

                                    if (tip == "Konumumu Kullan") {
                                        showDialog = true   // popup aç
                                    }
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFFEEA540),
                                    unselectedColor = Color.LightGray
                                )
                            )
                        }
                    }
                }
            }


            //İl
            item {
                ExposedDropdownMenuBox(
                    expanded = ilExpanded,
                    onExpandedChange = { ilExpanded = !ilExpanded }
                ) {
                    OutlinedTextField(
                        value = selectedIl,
                        onValueChange = {},
                        label = {
                            Text(
                                text = "İl seçiniz",
                                color = Color(0xFFA9B3BB)
                            )
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 10.dp),
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = ilExpanded) }
                    )
                    ExposedDropdownMenu(
                        expanded = ilExpanded,
                        onDismissRequest = { ilExpanded = false }
                    ) {
                        iller.forEach { il ->
                            DropdownMenuItem(
                                text = { Text(il) },
                                onClick = {
                                    selectedIl = il
                                    ilExpanded = false
                                }
                            )
                        }
                    }
                }
            }


            // İlçe
            item {
                ExposedDropdownMenuBox(
                    expanded = ilceExpanded,
                    onExpandedChange = { ilceExpanded = !ilceExpanded }
                ) {
                    OutlinedTextField(
                        value = selectedIlce,
                        onValueChange = {},
                        label = { Text("İlçe", color=Color(0xFF486072)) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(PaddingValues(start = 12.dp, end = 12.dp, top = 15.dp)),
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = ilceExpanded) }
                    )
                    ExposedDropdownMenu(
                        expanded = ilceExpanded,
                        onDismissRequest = { ilceExpanded = false }
                    ) {
                        ilceler.forEach { ilce ->
                            DropdownMenuItem(
                                text = { Text(ilce) },
                                onClick = {
                                    selectedIlce = ilce
                                    ilceExpanded = false
                                }
                            )
                        }
                    }
                }
            }


            // Belde
            item {
                ExposedDropdownMenuBox(
                    expanded = beldeExpanded,
                    onExpandedChange = { beldeExpanded = !beldeExpanded },
                    modifier = Modifier.fillMaxWidth() // Box’a padding kaldırıldı
                ) {
                    OutlinedTextField(
                        value = selectedBelde,
                        onValueChange = {},
                        label = { Text("Belde", color=Color(0xFF486072)) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 15.dp),
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = beldeExpanded) }
                    )
                    ExposedDropdownMenu(
                        expanded = beldeExpanded,
                        onDismissRequest = { beldeExpanded = false }
                    ) {
                        beldeler.forEach { belde ->
                            DropdownMenuItem(
                                text = { Text(belde) },
                                onClick = {
                                    selectedBelde = belde
                                    beldeExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            // Mahalle
            item {
                ExposedDropdownMenuBox(
                    expanded = mahalleExpanded,
                    onExpandedChange = { mahalleExpanded = !mahalleExpanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = selectedMahalle,
                        onValueChange = {},
                        label = { Text("Mahalle", color=Color(0xFF486072)) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 15.dp),
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = mahalleExpanded) }
                    )
                    ExposedDropdownMenu(
                        expanded = mahalleExpanded,
                        onDismissRequest = { mahalleExpanded = false }
                    ) {
                        mahalleler.forEach { mahalle ->
                            DropdownMenuItem(
                                text = { Text(mahalle) },
                                onClick = {
                                    selectedMahalle = mahalle
                                    mahalleExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            // Sokak
            item {
                ExposedDropdownMenuBox(
                    expanded = sokakExpanded,
                    onExpandedChange = { sokakExpanded = !sokakExpanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = selectedSokak,
                        onValueChange = {},
                        label = { Text("Sokak", color=Color(0xFF486072)) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 15.dp),
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sokakExpanded) }
                    )
                    ExposedDropdownMenu(
                        expanded = sokakExpanded,
                        onDismissRequest = { sokakExpanded = false }
                    ) {
                        sokaklar.forEach { sokak ->
                            DropdownMenuItem(
                                text = { Text(sokak) },
                                onClick = {
                                    selectedSokak = sokak
                                    sokakExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            // Bina No (isteğe bağlı)
            item {
                OutlinedTextField(
                    value = selectedBina,
                    onValueChange = { newValue -> selectedBina = newValue },
                    label = { Text("Bina No (isteğe bağlı)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, top = 15.dp, bottom = 15.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
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
                        onClick = { navController.navigate(Screen.ArızaTipi.route) },
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
                        onClick = { navController.navigate(Screen.Detaylar.route) },
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
                                text = "Devam et",
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
        // --- Popup burada, LazyColumn dışında ---
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            navController.navigate(Screen.Detaylar.route)},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3BC45)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Tamam", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "İptal", color = Color.Black)
                    }
                },
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.warning),
                            contentDescription = "Konum",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(56.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Konumumu Kullan",
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                    }
                },
                text = {
                    Text(
                        text = "Uygulamanın bulunduğunuz konuma erişmesine izin vermek istiyor musunuz?",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color(0xFF486072)
                    )
                },
                shape = RoundedCornerShape(12.dp)
            )
        }

    }
}

