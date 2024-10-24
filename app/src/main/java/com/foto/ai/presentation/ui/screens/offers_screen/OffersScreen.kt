package com.foto.ai.presentation.ui.screens.offers_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foto.ai.R
import com.foto.ai.domain.model.Offer
import com.foto.ai.presentation.ui.components.OfferPager
import com.foto.ai.presentation.ui.components.Space
import com.foto.ai.presentation.ui.theme.AppTheme

@Composable
fun OffersScreen(modifier: Modifier, onSubscriptionClick: (Offer) -> Unit) {
    var isYearlySelected by remember { mutableStateOf(true) }
    Surface(modifier.fillMaxSize()) {
        Column {
            PlanTypeSection(isYearlySelected = isYearlySelected, changeYearlySelection = {
                isYearlySelected = it
            })
            OffersSection(Modifier.fillMaxSize(), isYearlySelected = isYearlySelected, onSubscriptionClick= { onSubscriptionClick(it) })
        }
    }
}

@Composable
fun PlanTypeSection(isYearlySelected: Boolean, changeYearlySelection: (Boolean) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            FilterChip(
                onClick = {
                    changeYearlySelection(false)
                },
                label = {
                    Text(
                        "Monthly",
                        fontSize = 18.sp,
                        fontWeight = if (!isYearlySelected) FontWeight.Bold else FontWeight.Normal,
                        textAlign = TextAlign.Center,
                    )
                },
                selected = !isYearlySelected,
                modifier = Modifier.height(48.dp)
            )

            Space(6)

            FilterChip(
                onClick = {
                    changeYearlySelection(true)
                },
                label = {
                    Text(
                        "Yearly: %50 OFF",
                        fontSize = 18.sp,
                        fontWeight = if (isYearlySelected) FontWeight.Bold else FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                selected = isYearlySelected,
                leadingIcon = {
                    if (isYearlySelected) {
                        Icon(
                            painterResource(R.drawable.icon_fire),
                            contentDescription = "Fire Icon"
                        )
                    } else {
                        Icon(
                            painterResource(R.drawable.icon_fire),
                            contentDescription = "Fire Icon"
                        )
                    }
                },
                modifier = Modifier.height(48.dp)
            )
        }
    }
}

@Composable
fun OffersSection(modifier: Modifier = Modifier, isYearlySelected: Boolean, onSubscriptionClick: (Offer) -> Unit) {
    val offerList = listOf(
        Offer(
            "Starter Plan", 9, listOf(
                Offer.Feature("Take 100 AI Photos", "", false),
                Offer.Feature("Create 1 AI Model per month", "", false),
                Offer.Feature("Stable Diffusion: worse legacy model", "", false),
                Offer.Feature("Take 1 photo at a time", "", false),
                Offer.Feature("Use any photo pack", "", false),
                Offer.Feature("For personal use only", "", false),
                Offer.Feature("Watermarked photos", "", false)
            )
        ),
        Offer(
            "Pro Plan", 29, listOf(
                Offer.Feature("Take 1,000 AI Photos", "", true),
                Offer.Feature("3 AI Model per month", "", true),
                Offer.Feature("FLUX: New ultra realistic model", "", true),

                Offer.Feature("10x free profile pictures", "", false),
                Offer.Feature("10x free professional headshots", "", false),
                Offer.Feature("10x free dating app shots", "", false),
                Offer.Feature("10x free outfit ideas", "", false),
                Offer.Feature("10x free social media posts", "", false),

                Offer.Feature("All Starter features, plus:", "", true),

                Offer.Feature("Take up to 5 photos in the same time", "", false),
                Offer.Feature("Write your own prompts", "", false),
                Offer.Feature("Copy any photo", "", false),
                Offer.Feature("Commercial use", "", false)
            )
        ),
        Offer(
            "Premium Plan", 39, listOf(
                Offer.Feature("Take 5,000 AI Photos", "", true),
                Offer.Feature("10 AI Model per month", "", true),
                Offer.Feature("FLUX: New ultra realistic model", "", true),

                Offer.Feature("20x free profile pictures", "", false),
                Offer.Feature("20x free professional headshots", "", false),
                Offer.Feature("20x free dating app shots", "", false),
                Offer.Feature("20x free outfit ideas", "", false),
                Offer.Feature("20x free social media posts", "", false),

                Offer.Feature("All Pro features, plus:", "", true),

                Offer.Feature("Take up to 10 photos parallel", "", false),
                Offer.Feature("Use the magic photo editor", "", false),
                Offer.Feature("Try on clothes", "", false),
                Offer.Feature("Make videos from photos", "", false),
                Offer.Feature("Early access to the new features", "", false)
            )
        ),
    )
    OfferPager(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp),
        offerList = offerList,
        initialIndex = 1,
        onSubscriptionClick = { onSubscriptionClick(it) }
    )
}

@Preview
@Composable
private fun PreviewOffersScreen() {
    AppTheme {
        OffersScreen(Modifier) { }
    }
}