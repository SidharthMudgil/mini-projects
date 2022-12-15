import 'package:flutter/material.dart';
import 'package:recipe_app/screen/recipe_screen.dart';
import '../model/meal_model.dart';

class MealItem extends StatelessWidget {
  final MealModel _mealModel;

  const MealItem(this._mealModel, {Key? key})
      : super(key: key);

  void onMealItemClicked(BuildContext context) {
    Navigator.of(context).pushNamed(
      RecipeScreen.routeName,
      arguments: _mealModel,
    )
        /*  .then((value) {
      {
        if (value != null) {
          value = value as MealModel;
          removeMeal(value);
          debugPrint(value.title);
        }
      }
    })*/
        ;
  }

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () => onMealItemClicked(context),
      child: Card(
        margin: const EdgeInsets.all(10),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        child: Column(
          children: [
            Stack(
              children: [
                ClipRRect(
                  borderRadius: const BorderRadius.only(
                    topLeft: Radius.circular(10),
                    topRight: Radius.circular(10),
                  ),
                  child: Image.network(
                    _mealModel.imageUrl,
                    height: 250,
                    width: double.infinity,
                    fit: BoxFit.cover,
                  ),
                ),
                Positioned(
                  bottom: 20,
                  right: 0,
                  child: Container(
                    width: 220,
                    padding:
                        const EdgeInsets.symmetric(vertical: 10, horizontal: 5),
                    color: Colors.black45,
                    child: Text(
                      _mealModel.title,
                      style: const TextStyle(color: Colors.white, fontSize: 18),
                      softWrap: true,
                      overflow: TextOverflow.fade,
                    ),
                  ),
                ),
              ],
            ),
            Padding(
              padding: const EdgeInsets.all(10),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Row(
                    children: [
                      const Icon(Icons.schedule),
                      const SizedBox(width: 6),
                      Text('${_mealModel.duration} min'),
                    ],
                  ),
                  Row(
                    children: [
                      const Icon(Icons.work),
                      const SizedBox(width: 6),
                      Text(_mealModel.complexity.name),
                    ],
                  ),
                  Row(
                    children: [
                      const Icon(Icons.attach_money),
                      const SizedBox(width: 6),
                      Text(_mealModel.affordability.name),
                    ],
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
