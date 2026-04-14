package test_tasks.workmate_test_task.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test_tasks.workmate_test_task.R

@Composable
fun SectionTitle(title: String) {
	Text(
		text = title,
		fontSize = 18.sp,
		fontWeight = FontWeight.Bold,
		modifier = Modifier.padding(vertical = 4.dp)
	)
}

@Composable
fun InfoCard(label: String, value: String) {
	Card(
		modifier = Modifier.fillMaxWidth(),
		shape = RoundedCornerShape(12.dp),
		colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
	) {
		Column(modifier = Modifier.padding(12.dp)) {
			Text(text = label, fontSize = 14.sp, color = Color.Gray)
			Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
		}
	}
}

@Composable
fun EmptyInfoCard(message: String) {
	Card(
		modifier = Modifier.fillMaxWidth(),
		shape = RoundedCornerShape(12.dp),
		colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
	) {
		Box(Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
			Text(text = message, fontSize = 14.sp, color = Color.Gray)
		}
	}
}

@Composable
fun ClickableFilmCard(title: String, onClick: () -> Unit) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.clickable(onClick = onClick),
		shape = RoundedCornerShape(12.dp),
		colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
	) {
		Row(
			modifier = Modifier
				.padding(16.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3949AB))
			Icon(
				painter = painterResource(id = R.drawable.ic_arrow_open),
				contentDescription = "Open",
				tint = Color(0xFF3949AB),
				modifier = Modifier
					.size(20.dp)
					.rotate(180f)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun InfoCardPreview() {
	InfoCard("Birth Year", "19BBY")
}

@Preview(showBackground = true)
@Composable
fun EmptyInfoCardPreview() {
	EmptyInfoCard("No films available")
}

@Preview(showBackground = true)
@Composable
fun ClickableFilmCardPreview() {
	ClickableFilmCard("A New Hope") {}
}
