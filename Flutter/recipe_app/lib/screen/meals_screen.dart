import 'package:flutter/material.dart';
import '../widget/meal_item.dart';
import '../model/category_model.dart';
import '../model/meal_model.dart';

class MealsScreen extends StatefulWidget {
  static const routeName = '/meals-screen';
  final List<MealModel> meals;

  const MealsScreen(this.meals, {super.key});

  @override
  State<MealsScreen> createState() => _MealsScreenState();
}

class _MealsScreenState extends State<MealsScreen> {
  late CategoryModel category;
  late List<MealModel> meals;

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    category = ModalRoute.of(context)!.settings.arguments as CategoryModel;

    meals = widget.meals.where((meal) {
      return meal.categories.contains(category.id);
    }).toList();
  }

  // void removeMeal(MealModel model) {
  //   setState(() {
  //     meals.removeWhere((element) => model.id == element.id);
  //   });
  // }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          category.title,
        ),
      ),
      body: ListView.builder(
        itemBuilder: (context, index) =>
            MealItem(meals.elementAt(index)),
        itemCount: meals.length,
      ),
    );
  }
}
