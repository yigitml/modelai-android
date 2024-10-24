package com.foto.ai.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foto.ai.R
import com.foto.ai.domain.model.Offer
import com.foto.ai.presentation.ui.theme.AppTheme

@Composable
fun FeatureText(modifier: Modifier = Modifier, feature: Offer.Feature) {
   Row(modifier = modifier.padding(vertical = 4.dp),
       verticalAlignment = Alignment.CenterVertically,
       horizontalArrangement = Arrangement.Start) {
       Icon(
           painter = painterResource(R.drawable.icon_check_circle),
           modifier = Modifier,
           contentDescription = "Check (circle) Icon"
       )

       Space(1)

       Text(
           text = feature.title,
           style = MaterialTheme.typography.bodyLarge,
           fontWeight = if (feature.isEnhanced) FontWeight.Black else FontWeight.Normal,
           color = if (feature.isEnhanced) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
       )
   }
}

@Preview
@Composable
private fun PreviewFeatureText() {
    AppTheme(
        darkTheme = true
    ) {
        Surface(
        ) {
            FeatureText(feature = Offer.Feature("Stable Diffusion", "", true))
        }
    }
}