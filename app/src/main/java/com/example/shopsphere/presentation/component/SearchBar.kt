package com.example.shopsphere.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    // 1. States मैनेज करना
    var text by rememberSaveable { mutableStateOf("") } // सर्च इनपुट टेक्स्ट
    var active by rememberSaveable { mutableStateOf(false) } // सर्च बार ओपन है या नहीं

    // 1. Search History ki list (Shopping Items)
    val searchHistory = remember {
        mutableStateListOf("Mobile", "Laptop", "Smart TV", "Refrigerator")
    }

// 2. All Items ki static list (Shopping Suggestions/Catalog)
    val allItems = listOf(
        "Samsung Galaxy Mobile",
        "Apple MacBook Laptop",
        "LG Double Door Refrigerator",
        "Sony 4K Smart TV",
        "Sony Wireless Headphones",
        "iPad Air Tablet",
        "Fully Automatic Washing Machine"
    )

    // लाइव फ़िल्टर किया हुआ डेटा
    val filteredItems = allItems.filter { it.contains(text, ignoreCase = true) }

    Box(modifier = modifier.fillMaxWidth()) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (active) 0.dp else 16.dp, vertical = 8.dp),
            query = text,
            onQueryChange = { text = it }, // टाइप करने पर टेक्स्ट अपडेट होगा
            onSearch = { query ->
                if (query.trim().isNotEmpty() && !searchHistory.contains(query)) {
                    searchHistory.add(0, query) // हिस्ट्री में टॉप पर ऐड करें
                }
                active = false // सर्च करने के बाद कीबोर्ड/एरिया क्लोज करें
            },
            active = active,
            onActiveChange = { active = it }, // फोकस होने पर एक्टिव स्टेट चेंज होगी
            placeholder = { Text(text = "Search your product") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = "" // टेक्स्ट क्लियर करें
                            } else {
                                active = false // खाली होने पर सर्च बार बंद करें
                            }
                        },
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear or Close Icon"
                    )
                }
            }
        ) {
            // 2. सर्च बार के अंदर का कंटेंट (सजेशन या हिस्ट्री)
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (text.isEmpty()) {
                    // जब यूजर ने कुछ टाइप नहीं किया, तो 'सर्च हिस्ट्री' दिखाएं
                    items(searchHistory) { historyItem ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    text = historyItem
                                    active = true
                                }
                                .padding(vertical = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = "History",
                                modifier = Modifier.padding(end = 12.dp)
                            )
                            Text(text = historyItem)
                        }
                    }
                } else {
                    // जब यूजर टाइप कर रहा हो, तो 'लाइव फ़िल्टर्ड रिजल्ट्स' दिखाएं
                    items(filteredItems) { item ->
                        Text(
                            text = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    text = item
                                    active = false
                                }
                                .padding(vertical = 12.dp, horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
