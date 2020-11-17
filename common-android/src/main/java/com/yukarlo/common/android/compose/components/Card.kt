package com.yukarlo.common.android.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yukarlo.common.android.compose.theme.CoronowTheme
import com.yukarlo.common.android.compose.theme.shapes

@Composable
fun CoronowCard(
    modifier: Modifier = Modifier,
    shape: Shape = shapes.small,
    backgroundColor: Color,
    contentColor: Color = CoronowTheme.colors.cardTextPrimary,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    CoronowSurface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        content = content
    )
}

@Composable
fun casesCard(
    text: String,
    count: String,
    backgroundColor: Color,
    cardModifier: Modifier
) {
    CoronowCard(
        backgroundColor = backgroundColor,
        modifier = cardModifier,
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                start = 10.dp,
                end = 10.dp,
                top = 40.dp,
                bottom = 40.dp
            )
        ) {
            val (
                textLabel,
                textCount
            ) = createRefs()

            Text(
                text = count,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(ref = textCount) {
                    top.linkTo(anchor = parent.top)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = textLabel.top)
                }
            )
            Text(
                text = text,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(ref = textLabel) {
                    top.linkTo(anchor = textCount.bottom)
                    start.linkTo(anchor = textCount.start)
                    end.linkTo(anchor = textCount.end)
                    bottom.linkTo(anchor = parent.bottom)
                }
            )
        }
    }
}