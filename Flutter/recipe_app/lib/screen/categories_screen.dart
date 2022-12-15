import 'package:flutter/material.dart';
import '../dummy_data.dart';
import '../widget/category_item.dart';

class CategoryScreen extends StatelessWidget {
  const CategoryScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      itemCount: dummyCategories.length,
      padding: const EdgeInsets.all(10),
      gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
        maxCrossAxisExtent: 200,
        childAspectRatio: 300 / 200,
        crossAxisSpacing: 10,
        mainAxisSpacing: 10,
      ),
      itemBuilder: (context, index) =>
          CategoryItem(dummyCategories.elementAt(index)),
      // children: dummyData.map((e) => CategoryItem(e)).toList(),
    );
  }
}
