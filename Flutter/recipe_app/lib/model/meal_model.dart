enum Complexity {
  novice,
  advancedBeginner,
  competent,
  proficient,
  expert,
}

enum Affordability {
  cheap,
  affordable,
  costly,
}

class MealModel {
  final String id;
  final String title;
  final String imageUrl;

  final int duration;
  final Complexity complexity;
  final Affordability affordability;

  final bool isGlutenFree;
  final bool isLactoseFree;
  final bool isVegan;
  final bool isVegetarian;

  final List<String> ingredients;
  final List<String> procedure;
  final List<String> categories;

  const MealModel({
    required this.id,
    required this.title,
    required this.imageUrl,
    required this.duration,
    required this.complexity,
    required this.affordability,
    required this.isGlutenFree,
    required this.isLactoseFree,
    required this.isVegan,
    required this.isVegetarian,
    required this.ingredients,
    required this.procedure,
    required this.categories,
  });
}
