package com.fennec.hirfa.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fennec.hirfa.presentation.common.CustomTextField
import com.fennec.hirfa.presentation.common.GradientButton
import com.fennec.hirfa.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun ProfileSetupScreen(
    userType: String,
    onProfileComplete: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val isServiceProvider = userType.equals("service_provider", ignoreCase = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Complete Your Profile",
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (isServiceProvider)
                "Let customers know about your services"
            else
                "Help us serve you better",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Profile Image Placeholder
        Card(
            modifier = Modifier.size(120.dp),
            colors = CardDefaults.cardColors(containerColor = Primary.copy(alpha = 0.1f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📷",
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { /* Handle image selection */ }
        ) {
            Text(
                text = "Add Profile Photo",
                color = Primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Form Fields
        CustomTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Full Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = phone,
            onValueChange = { phone = it },
            label = "Phone Number",
            leadingIcon = Icons.Default.Phone,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = location,
            onValueChange = { location = it },
            label = "Location",
            leadingIcon = Icons.Default.LocationOn,
            modifier = Modifier.fillMaxWidth()
        )

        if (isServiceProvider) {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Tell us about your services") },
                maxLines = 4,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = androidx.compose.ui.graphics.Color.Gray.copy(alpha = 0.3f)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Complete Profile Button
        GradientButton(
            text = "Complete Profile",
            onClick = {
                isLoading = true
                // Simulate profile completion delay
                kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
                    kotlinx.coroutines.delay(1500)
                    onProfileComplete()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            isLoading = isLoading,
            enabled = fullName.isNotEmpty() && phone.isNotEmpty() && location.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}