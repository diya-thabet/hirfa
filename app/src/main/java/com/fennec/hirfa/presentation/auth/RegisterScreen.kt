package com.fennec.hirfa.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fennec.hirfa.presentation.common.CustomTextField
import com.fennec.hirfa.presentation.common.GradientButton
import com.fennec.hirfa.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateBack: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Primary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Join thousands of satisfied users",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Full Name Field
            CustomTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    nameError = ""
                },
                label = "Full Name",
                leadingIcon = Icons.Default.Person,
                isError = nameError.isNotEmpty(),
                errorMessage = nameError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email Field
            CustomTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = ""
                },
                label = "Email Address",
                leadingIcon = Icons.Default.Email,
                isError = emailError.isNotEmpty(),
                errorMessage = emailError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = ""
                },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = if (passwordError.isNotEmpty()) Error else Primary
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordError.isNotEmpty(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f),
                    errorBorderColor = Error
                ),
                modifier = Modifier.fillMaxWidth()
            )

            if (passwordError.isNotEmpty()) {
                Text(
                    text = passwordError,
                    style = MaterialTheme.typography.bodySmall,
                    color = Error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password Field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = ""
                },
                label = { Text("Confirm Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = if (confirmPasswordError.isNotEmpty()) Error else Primary
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = confirmPasswordError.isNotEmpty(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f),
                    errorBorderColor = Error
                ),
                modifier = Modifier.fillMaxWidth()
            )

            if (confirmPasswordError.isNotEmpty()) {
                Text(
                    text = confirmPasswordError,
                    style = MaterialTheme.typography.bodySmall,
                    color = Error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Register Button
            GradientButton(
                text = "Create Account",
                onClick = {
                    // Validate inputs
                    var hasError = false

                    if (fullName.isEmpty()) {
                        nameError = "Full name is required"
                        hasError = true
                    }

                    if (email.isEmpty()) {
                        emailError = "Email is required"
                        hasError = true
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = "Invalid email format"
                        hasError = true
                    }

                    if (password.isEmpty()) {
                        passwordError = "Password is required"
                        hasError = true
                    } else if (password.length < 6) {
                        passwordError = "Password must be at least 6 characters"
                        hasError = true
                    }

                    if (confirmPassword.isEmpty()) {
                        confirmPasswordError = "Please confirm your password"
                        hasError = true
                    } else if (password != confirmPassword) {
                        confirmPasswordError = "Passwords don't match"
                        hasError = true
                    }

                    if (!hasError) {
                        isLoading = true
                        // Simulate registration delay
                        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
                            kotlinx.coroutines.delay(1500)
                            onRegisterSuccess()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                isLoading = isLoading
            )

            Spacer(modifier = Modifier.weight(1f))

            // Sign in link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
                TextButton(
                    onClick = onNavigateBack
                ) {
                    Text(
                        text = "Sign In",
                        color = Primary,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}