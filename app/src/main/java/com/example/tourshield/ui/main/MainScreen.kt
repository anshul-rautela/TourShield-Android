package com.example.tourshield.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import com.example.tourshield.R
import com.example.tourshield.theme.CardSurface
import com.example.tourshield.theme.ChevronColor
import com.example.tourshield.theme.DarkBackground
import com.example.tourshield.theme.DividerColor
import com.example.tourshield.theme.EmergencyRed
import com.example.tourshield.theme.LiveSosText
import com.example.tourshield.theme.SectionHeaderColor
import com.example.tourshield.theme.SettingsBlue
import com.example.tourshield.theme.SwitchThumbDark
import com.example.tourshield.theme.SwitchTrackBlue
import com.example.tourshield.theme.TextMuted
import com.example.tourshield.theme.TextPrimary
import com.example.tourshield.theme.TextSubdued
import com.example.tourshield.theme.TourShieldTheme

// ─────────────────────────────────────────────────────────────────────────────
// Inline Canvas icons
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun CheckIcon(tint: Color, size: Dp = 16.dp) {
    Canvas(Modifier.size(size)) {
        val w = this.size.width; val h = this.size.height
        val stroke = Stroke(width = w * 0.12f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawPath(Path().apply {
            moveTo(w * 0.15f, h * 0.52f)
            lineTo(w * 0.42f, h * 0.75f)
            lineTo(w * 0.85f, h * 0.28f)
        }, tint, style = stroke)
    }
}

@Composable
private fun ChevronRightIcon(tint: Color, size: Dp = 20.dp) {
    Canvas(Modifier.size(size)) {
        val w = this.size.width; val h = this.size.height
        val stroke = Stroke(width = w * 0.12f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawPath(Path().apply {
            moveTo(w * 0.35f, h * 0.2f)
            lineTo(w * 0.65f, h * 0.5f)
            lineTo(w * 0.35f, h * 0.8f)
        }, tint, style = stroke)
    }
}

@Composable
private fun SettingsIcon(tint: Color, size: Dp = 20.dp) {
    Canvas(Modifier.size(size)) {
        val cx = this.size.width / 2f; val cy = this.size.height / 2f
        val outerR = this.size.width * 0.44f
        val valleyR = this.size.width * 0.33f
        val holeR   = this.size.width * 0.16f
        val teeth   = 8; val halfTooth = Math.toRadians(14.0)
        val gearPath = Path().apply {
            for (i in 0 until teeth) {
                val baseRad = Math.toRadians(i * 360.0 / teeth)
                val a1   = (baseRad - halfTooth).toFloat()
                val a2   = (baseRad + halfTooth).toFloat()
                val aMid = (baseRad + Math.toRadians(360.0 / teeth / 2)).toFloat()
                val x1 = cx + outerR * kotlin.math.cos(a1); val y1 = cy + outerR * kotlin.math.sin(a1)
                val x2 = cx + outerR * kotlin.math.cos(a2); val y2 = cy + outerR * kotlin.math.sin(a2)
                val xV = cx + valleyR * kotlin.math.cos(aMid); val yV = cy + valleyR * kotlin.math.sin(aMid)
                if (i == 0) moveTo(x1, y1) else lineTo(x1, y1)
                lineTo(x2, y2); lineTo(xV, yV)
            }
            close()
            addOval(Rect(cx - holeR, cy - holeR, cx + holeR, cy + holeR))
        }
        drawPath(gearPath, tint, style = androidx.compose.ui.graphics.drawscope.Fill)
    }
}

@Composable
private fun PulseIcon(tint: Color, size: Dp = 16.dp) {
    Canvas(Modifier.size(size)) {
        val cx = this.size.width / 2f; val cy = this.size.height / 2f
        drawCircle(color = tint.copy(alpha = 0.3f), radius = this.size.width * 0.45f,
            center = Offset(cx, cy), style = Stroke(width = this.size.width * 0.06f))
        drawCircle(color = tint.copy(alpha = 0.6f), radius = this.size.width * 0.30f,
            center = Offset(cx, cy), style = Stroke(width = this.size.width * 0.06f))
        drawCircle(color = tint, radius = this.size.width * 0.14f, center = Offset(cx, cy))
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// ViewModel entry point
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun MainScreen(
    onItemClick: (NavKey) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        sosEnabled            = state.manualSosEnabled,
        minutesAgo            = state.minutesAgo,
        liveSosEnabled        = state.liveSosEnabled,
        liveSosSecondsElapsed = state.liveSosSecondsElapsed,
        onSosToggle           = viewModel::onSosToggle,
        onLiveSosToggle       = viewModel::onLiveSosToggle,
        modifier              = modifier,
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// Reusable Section Header with uniform title & subtitle styling
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 2.dp, bottom = 12.dp),
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = subtitle,
            fontSize = 14.sp,
            color = TextSubdued,
            lineHeight = 20.sp,
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Stateless screen
// ─────────────────────────────────────────────────────────────────────────────

@Composable
internal fun HomeScreen(
    sosEnabled: Boolean,
    minutesAgo: Int = 7,
    onSosToggle: (Boolean) -> Unit,
    liveSosEnabled: Boolean = false,
    liveSosSecondsElapsed: Int = 0,
    onLiveSosToggle: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
    ) {
        Spacer(Modifier.height(56.dp))

        // Header
        Text(
            text = "Hello User",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(top = 12.dp, bottom = 28.dp),
        )

        // ── Manual SOS Section (Uniform header & policy description) ─────────
        SectionHeader(
            title = "Manual SOS",
            subtitle = "Under emergency policy, triggering Manual SOS immediately alerts emergency dispatch authorities and broadcasts your real-time GPS location until toggled off.",
        )

        // ── Manual SOS Card (same style & size as Father row, with end toggle) ─
        ManualSosCard(
            active = sosEnabled,
            onToggle = onSosToggle,
        )

        Spacer(Modifier.height(28.dp))

        // ── Contacts Section (Uniform header & policy description) ───────────
        SectionHeader(
            title = "Contacts",
            subtitle = "Under safety policy, your live location and distress status will be automatically shared with these designated contacts in case of any detected anomaly.",
        )

        // ── Contacts card ────────────────────────────────────────────────────
        ContactsCard(minutesAgo = minutesAgo)

        Spacer(Modifier.height(36.dp))
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Manual SOS — styled same as Father row with end toggle switch
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun ManualSosCard(
    active: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = { onToggle(!active) },
        shape = RoundedCornerShape(20.dp),
        color = CardSurface,
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                role = Role.Switch
                contentDescription = if (active) {
                    "Manual SOS is active. Double tap to deactivate."
                } else {
                    "Manual SOS is inactive. Double tap to activate."
                }
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
        ) {
            // 44dp circular badge matching avatar size of Father & Mother
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(if (active) Color(0xFF381A1E) else Color(0xFF1B1F24))
                    .border(
                        width = 1.5.dp,
                        color = if (active) Color(0xFFEF4444) else Color(0xFFEF4444).copy(alpha = 0.7f),
                        shape = CircleShape,
                    ),
            ) {
                Text(
                    text = "SOS",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFEF4444),
                    letterSpacing = 1.sp,
                )
            }

            Spacer(Modifier.width(14.dp))

            Text(
                text = "Manual SOS",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary,
                modifier = Modifier.weight(1f),
            )

            // Toggle switch at the end
            Switch(
                checked = active,
                onCheckedChange = onToggle,
                thumbContent = if (active) {
                    { CheckIcon(tint = Color(0xFFEF4444), size = SwitchDefaults.IconSize) }
                } else null,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Color(0xFFEF4444),
                    checkedThumbColor = Color.White,
                    uncheckedTrackColor = TextMuted.copy(alpha = 0.35f),
                    uncheckedThumbColor = TextSubdued,
                    uncheckedBorderColor = Color.Transparent,
                    checkedBorderColor = Color.Transparent,
                ),
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Contacts card — Heading, description, and contact list
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun ContactsCard(
    minutesAgo: Int = 1,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface),
    ) {
        ContactRow(
            name = "Father",
            avatarRes = R.drawable.avatar_father,
        )

        HorizontalDivider(
            color = DividerColor,
            thickness = 0.6.dp,
            modifier = Modifier.padding(start = 74.dp),
        )

        ContactRow(
            name = "Mother",
            avatarRes = R.drawable.avatar_mother,
        )

        HorizontalDivider(
            color = DividerColor,
            thickness = 0.6.dp,
            modifier = Modifier.padding(start = 16.dp),
        )

        ShowAllRow()
    }
}

@Composable
private fun SharedLocationCard(modifier: Modifier = Modifier) {
    ContactsCard(minutesAgo = 1, modifier = modifier)
}

@Composable
private fun ContactRow(
    name: String,
    avatarRes: Int,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
    ) {
        androidx.compose.foundation.Image(
            painter = painterResource(id = avatarRes),
            contentDescription = "$name avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape),
        )
        Spacer(Modifier.width(14.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary,
            )
            if (!subtitle.isNullOrEmpty()) {
                Spacer(Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = TextMuted,
                )
            }
        }
        ChevronRightIcon(tint = ChevronColor, size = 20.dp)
        VerticalDivider(
            color = DividerColor,
            modifier = Modifier
                .height(28.dp)
                .padding(horizontal = 10.dp),
        )
        SettingsIcon(tint = SettingsBlue, size = 20.dp)
    }
}

@Composable
private fun ShowAllRow(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
    ) {
        ChevronRightIcon(tint = ChevronColor, size = 18.dp)
        Spacer(Modifier.width(10.dp))
        Text(text = "Show all", fontSize = 14.sp, color = TextSubdued)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Previews
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFF121619, name = "Home – Normal")
@Composable
private fun HomeScreenPreview() {
    TourShieldTheme {
        HomeScreen(
            sosEnabled  = true,
            minutesAgo  = 7,
            onSosToggle = {},
        )
    }
}
