package com.fennec.hirfa.presentation.usertype

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fennec.hirfa.data.model.UserType
import com.fennec.hirfa.presentation.common.GradientButton
import com.fennec.hirfa.ui.theme.*

@Composable
fun UserTypeSelectionScreen(
    onUserTypeSelected: (String) -> Unit
) {
    var selectedUserType by remember { mutableStateOf<UserType?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "How do you want to use Hirfa?",
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Choose your role to get the best experience",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Customer Option
        UserTypeCard(
            userType = UserType.CUSTOMER,
            isSelected = selectedUserType == UserType.CUSTOMER,
            onClick = { selectedUserType = UserType.CUSTOMER },
            icon = "🏠",
            description = "Book services from trusted professionals"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Service Provider Option
        UserTypeCard(
            userType = UserType.SERVICE_PROVIDER,
            isSelected = selectedUserType == UserType.SERVICE_PROVIDER,
            onClick = { selectedUserType = UserType.SERVICE_PROVIDER },
            icon = "🔧",
            description = "Offer your services and grow your business"
        )

        Spacer(modifier = Modifier.weight(1f))

        // Continue Button
        GradientButton(
            text = "Continue",
            onClick = {
                selectedUserType?.let { userType ->
                    onUserTypeSelected(userType.name.toLowerCase())
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedUserType != null
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun UserTypeCard(
    userType: UserType,
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: String,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                color = if (isSelected) Primary else Color.Gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Primary.copy(alpha = 0.1f) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = icon,
                fontSize = 48.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = userType.displayName,
                style = MaterialTheme.typography.titleLarge,
                color = if (isSelected) Primary else TextPrimary,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}