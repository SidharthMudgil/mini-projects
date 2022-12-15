import 'package:flutter/material.dart';
import '../screen/meals_screen.dart';
import '../model/category_model.dart';

class CategoryItem extends StatelessWidget {
  final CategoryModel _categoryModel;

  const CategoryItem(this._categoryModel, {Key? key}) : super(key: key);

  void onCategoryClick(BuildContext context) {
    debugPrint('${_categoryModel.title} clicked');
    // Navigator.of(context).push(
    //   MaterialPageRoute(
    //     builder: (_) => MealsScreen(),
    //   ),
    // );
    Navigator.of(context).pushNamed(
      MealsScreen.routeName,
      arguments: _categoryModel,
    );
  }

  @override
  Widget build(BuildContext context) {
    return InkWell(
      splashColor: Theme.of(context).colorScheme.secondary,
      onTap: () {
        onCategoryClick(context);
      },
      child: Container(
        padding: const EdgeInsets.all(10),
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [
              _categoryModel.color.withOpacity(0.4),
              _categoryModel.color.withOpacity(1),
            ],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
          borderRadius: BorderRadius.circular(14),
        ),
        child: Text(
          _categoryModel.title,
          style: Theme.of(context).textTheme.titleLarge?.copyWith(color: const Color.fromRGBO(70, 52, 24, 1)),
        ),
        // decoration: ,
      ),
    );
  }
}
