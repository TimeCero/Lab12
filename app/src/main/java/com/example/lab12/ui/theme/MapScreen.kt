package com.example.lab12.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker


@Composable
fun MapScreen() {
    val ArequipaLocation = LatLng(-16.4040102, -71.559611) // Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(ArequipaLocation, 12f)
    }

    //Podemos colocar aqui para agregar mas marcadores
    val locations = listOf(
        LatLng(-16.433415, -71.5442652), // JLByR
        LatLng(-16.4205151, -71.4945209), // Paucarpata
        LatLng(-16.3524187, -71.5675994)  // Zamacola
    )

    // Controlar la cámara para que se mueva a Yura al inicio
    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(LatLng(-16.2520984, -71.6836503), 12f), // Nueva ubicación en Yura
            durationMs = 3000 // Duración de la animación en milisegundos
        )

    }

    //parte 4
    // Definir los polígonos
    val mallAventuraPolygon = listOf(
        LatLng(-16.432292, -71.509145),
        LatLng(-16.432757, -71.509626),
        LatLng(-16.433013, -71.509310),
        LatLng(-16.432566, -71.508853)
    )

    val parqueLambramaniPolygon = listOf(
        LatLng(-16.422704, -71.530830),
        LatLng(-16.422920, -71.531340),
        LatLng(-16.423264, -71.531110),
        LatLng(-16.423050, -71.530600)
    )

    val plazaDeArmasPolygon = listOf(
        LatLng(-16.398866, -71.536961),
        LatLng(-16.398744, -71.536529),
        LatLng(-16.399178, -71.536289),
        LatLng(-16.399299, -71.536721)
    )


    // Definir las rutas para las polilíneas
    val rutaUno = listOf(
        LatLng(-16.4040102, -71.559611),
        LatLng(-16.4110102, -71.561611),
        LatLng(-16.4180102, -71.565611)
    )

    val rutaDos = listOf(
        LatLng(-16.4140102, -71.550611),
        LatLng(-16.4200102, -71.552611),
        LatLng(-16.4260102, -71.556611)
    )

    //Aqui aparece lo del marcador
    Box(modifier = Modifier.fillMaxSize()) {
        // Añadir GoogleMap al layout
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Añadir marcador en Denver, Colorado
            Marker(
                state = rememberMarkerState(position = ArequipaLocation),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE), // Icono azul
                title = "Arequipa, Perú"
            )

            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    title = "ubicacion",
                    snippet = "Punto de interés"
                )
            }

            // Dibujar polígonos
            com.google.maps.android.compose.Polygon(
                points = plazaDeArmasPolygon,
                strokeColor = androidx.compose.ui.graphics.Color.Red,
                fillColor = androidx.compose.ui.graphics.Color.Blue,
                strokeWidth = 5f
            )
            com.google.maps.android.compose.Polygon(
                points = parqueLambramaniPolygon,
                strokeColor = androidx.compose.ui.graphics.Color.Red,
                fillColor = androidx.compose.ui.graphics.Color.Blue,
                strokeWidth = 5f
            )
            com.google.maps.android.compose.Polygon(
                points = mallAventuraPolygon,
                strokeColor = androidx.compose.ui.graphics.Color.Red,
                fillColor = androidx.compose.ui.graphics.Color.Blue,
                strokeWidth = 5f
            )
            // Polilínea de Ruta Uno
            com.google.maps.android.compose.Polyline(
                points = rutaUno,
                color = androidx.compose.ui.graphics.Color.Green,
                width = 10f // Ancho de la línea
            )
            // Polilínea de Ruta Dos
            com.google.maps.android.compose.Polyline(
                points = rutaDos,
                color = androidx.compose.ui.graphics.Color.Magenta,
                width = 8f // Ancho de la línea
            )
        }
    }
}