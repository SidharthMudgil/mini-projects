import 'package:flutter/material.dart';
import 'package:recipe_app/widget/meal_item.dart';

import '../model/meal_model.dart';

class FavoritesScreen extends StatefulWidget {
  static const routeName = '/favorites_name';
  final List<MealModel> favorites;

  const FavoritesScreen(this.favorites, {Key? key}) : super(key: key);

  @override
  State<FavoritesScreen> createState() => _FavoritesScreenState();
}

class _FavoritesScreenState extends State<FavoritesScreen> {
  @override
  Widget build(BuildContext context) {
    if (widget.favorites.isEmpty) {
      return Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: const [
            Icon(Icons.face),
            Padding(
              padding: EdgeInsets.all(10),
              child: Text('No Favorite Meals'),
            ),
          ],
        ),
      );
    }
    // return Container();
    return ListView.builder(
      itemBuilder: (context, index) => MealItem(widget.favorites.elementAt(index)),
      itemCount: widget.favorites.length,
    );
  }
}
