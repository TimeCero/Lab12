package com.example.lab12.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreenWithBottomTypeSelector() {
    val arequipaLocation = LatLng(-16.4040102, -71.559611) // Ubicación inicial en Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(arequipaLocation, 12f)
    }

    // Estado para el tipo de mapa y visibilidad del menú
    val (mapType, setMapType) = remember { mutableStateOf(MapType.NORMAL) }
    val isDropdownOpen = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = mapType) // Cambia el tipo de mapa aquí
        )

        // Botón para abrir el menú en la parte inferior
        Button(
            onClick = { isDropdownOpen.value = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Cambiar tipo de mapa")
        }

        // Menú desplegable para seleccionar el tipo de mapa
        DropdownMenu(
            expanded = isDropdownOpen.value,
            onDismissRequest = { isDropdownOpen.value = false },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            // Usamos una función personalizada para el menú
            CustomDropdownMenuItem("Normal") {
                setMapType(MapType.NORMAL)
                isDropdownOpen.value = false
            }
            CustomDropdownMenuItem("Híbrido") {
                setMapType(MapType.HYBRID)
                isDropdownOpen.value = false
            }
            CustomDropdownMenuItem("Satélite") {
                setMapType(MapType.SATELLITE)
                isDropdownOpen.value = false
            }
            CustomDropdownMenuItem("Terreno") {
                setMapType(MapType.TERRAIN)
                isDropdownOpen.value = false
            }
        }
    }
}

// Función personalizada para el DropdownMenuItem
@Composable
fun CustomDropdownMenuItem(label: String, onClick: () -> Unit) {
    Text(
        text = label,
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() } // Hace que el texto sea clickeable
    )
}
