package no.ntnu.idatt2506.oppskrifter

data class Oppskrift(
    val navn: String,
    val kategori: String,
    val minutter: Int
)

val oppskrifter = listOf(
    Oppskrift("Fiskesuppe", "Hovedrett", 35),
    Oppskrift("Lapskaus", "Hovedrett", 90),
    Oppskrift("Vaffelrøre", "Dessert", 20),
    Oppskrift("Tomatsuppe", "Forrett", 25),
    Oppskrift("Eplekake", "Dessert", 55),
    Oppskrift("Fiskekaker", "Hovedrett", 40)
)
