import 'package:flutter/material.dart';
import 'package:recipe_app/widget/recipe_section.dart';
import '../model/meal_model.dart';

class RecipeScreen extends StatefulWidget {
  static const routeName = '/recipe-screen';
  final Function toggleFavorite;
  final Function isFavorite;

  const RecipeScreen(this.toggleFavorite, this.isFavorite, {super.key});

  @override
  State<RecipeScreen> createState() => _RecipeScreenState();
}

class _RecipeScreenState extends State<RecipeScreen> {
  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as MealModel;
    return Scaffold(
      appBar: AppBar(
        title: Text(args.title),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(10),
          child: Column(
            children: [
              Container(
                margin: const EdgeInsets.only(bottom: 10),
                height: 300,
                width: double.infinity,
                child: Image.network(
                  args.imageUrl,
                  fit: BoxFit.cover,
                ),
              ),
              RecipeSection('Ingredients', args.ingredients),
              RecipeSection('Procedure', args.procedure),
            ],
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(widget.isFavorite(args)
            ? Icons.favorite_rounded
            : Icons.favorite_outline_rounded),
        onPressed: () {
          setState(() {
            widget.toggleFavorite(args);
          });
        },
      ),
    );
  }
}
