package io.github.tritacle.recipecard

data class Recipe(
    val name: String,
    val category: String,
    val minutes: Int
)

val recipes = listOf(
    Recipe("Fiskesuppe", "Hovedrett", 35),
    Recipe("Lapskaus", "Hovedrett", 90),
    Recipe("Vaffelrøre", "Dessert", 20),
    Recipe("Tomatsuppe", "Forrett", 25),
    Recipe("Eplekake", "Dessert", 55),
    Recipe("Fiskekaker", "Hovedrett", 40)
)
