package no.ntnu.idatt2506.oppskrifter

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.ntnu.idatt2506.oppskrifter.ui.theme.OppskrifterTheme

@Composable
fun OppskriftSkjerm(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val antallTekst = stringResource(R.string.antall_toast, oppskrifter.size)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
    ) {
        Topp()
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(oppskrifter) { oppskrift ->
                OppskriftKort(oppskrift)
            }
        }
        Button(
            onClick = { Toast.makeText(context, antallTekst, Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = stringResource(R.string.knapp),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun Topp(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.topbilde),
            contentDescription = stringResource(R.string.bilde_beskrivelse),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = stringResource(R.string.merkelapp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        )
        Text(
            text = stringResource(R.string.tittel),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun OppskriftKort(oppskrift: Oppskrift, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val valgtTekst = stringResource(R.string.valgt_toast, oppskrift.navn)

    // Rekkefølgen teller: bakgrunn, så klikkflate, så luft. Legges padding før
    // clickable, blir kanten av kortet død og trykk der gir ingenting.
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .clickable { Toast.makeText(context, valgtTekst, Toast.LENGTH_SHORT).show() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = oppskrift.navn,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = oppskrift.kategori,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = stringResource(R.string.tid, oppskrift.minutter),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OppskriftSkjermPreview() {
    OppskrifterTheme {
        OppskriftSkjerm()
    }
}

@Preview(showBackground = true)
@Composable
fun OppskriftKortPreview() {
    OppskrifterTheme {
        OppskriftKort(oppskrifter.first())
    }
}
