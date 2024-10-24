package com.foto.ai.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foto.ai.R
import com.foto.ai.domain.model.Offer

@Composable
fun OfferPager(
    modifier: Modifier,
    offerList: List<Offer>,
    initialIndex: Int,
    onSubscriptionClick: (Offer) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = initialIndex, pageCount = { offerList.size })

    Box(modifier.fillMaxWidth()) {
        OfferCarousel(Modifier.align(Alignment.Center), offerList, pagerState) {
            onSubscriptionClick(
                offerList[pagerState.currentPage]
            )
        }
    }
}

@Composable
fun OfferCarousel(
    modifier: Modifier,
    offerList: List<Offer>,
    pagerState: PagerState,
    onSubscriptionClick: () -> Unit
) {
    var cardHeight by remember { mutableStateOf(0.dp) }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) { page ->
        BoxWithConstraints {
            OfferCard(offerList[page], onSubscriptionClick = onSubscriptionClick)
            cardHeight = maxHeight
        }
    }
}

@Composable
fun OfferCard(offer: Offer, onSubscriptionClick: () -> Unit) {
    ElevatedCard (modifier = Modifier.padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(offer.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Space(8)
            OfferPricing(offer.fee)
            Space(6)
            SubscribeButton(onClick = { onSubscriptionClick() })
            Space(8)
            FeaturesList(offer.features)
            Space(2)
        }
    }
}

@Composable
fun OfferPricing(fee: Int) {
    Row(
        modifier = Modifier
            .padding(start = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$fee$", style = MaterialTheme.typography.headlineLarge, fontSize = 48.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Left)
        Space(6)
        Column {
            Text("FREE for 6 months", style = MaterialTheme.typography.bodyMedium)
            Text("billed yearly", style = MaterialTheme.typography.bodyMedium)
            Text("per month", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.padding(bottom = 8.dp))
        }
    }
}

@Composable
fun SubscribeButton(onClick: () -> Unit) {
    Button(
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        modifier = Modifier
            .widthIn(max=320.dp)
            .fillMaxWidth()
            .height(72.dp)
    ) {
        Text(
            "Subscribe",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 24.sp
        )
        Space(4)
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_forward), // Replace with your actual drawable
            contentDescription = "Subscribe"
        )
    }
}

@Composable
fun FeaturesList(featureList: List<Offer.Feature>) {
    var expanded by remember { mutableStateOf(false) }
    val showAmount = 7
    val itemsToShow = if (expanded) featureList else featureList.take(showAmount)
    LazyColumn {
        items(itemsToShow) { feature ->
            FeatureText(feature = feature)
        }
        item {
            if (featureList.size > showAmount) {
                Button(onClick = { expanded = !expanded }) {
                    Text(if (expanded) "Show Less" else "Read More")
                }
            }
        }
    }
}