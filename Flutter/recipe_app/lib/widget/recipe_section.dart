import 'package:flutter/material.dart';

class RecipeSection extends StatelessWidget {
  final String title;
  final List<String> items;

  const RecipeSection(this.title, this.items, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          margin: const EdgeInsets.only(bottom: 10),
          child: Text(
            title,
            style: Theme.of(context).textTheme.titleMedium,
          ),
        ),
        Container(
          decoration: BoxDecoration(
            border: Border.all(color: const Color.fromRGBO(182, 175, 156, 1.0)),
            borderRadius: BorderRadius.circular(10),
          ),
          height: 200,
          width: MediaQuery.of(context).size.width - 40,
          margin: const EdgeInsets.only(bottom: 10, left: 10, right: 10),
          child: ListView.builder(
            itemCount: items.length,
            itemBuilder: (context, index) => Card(
              color: Colors.deepOrangeAccent,
              child: Padding(
                padding: const EdgeInsets.all(10),
                child: Text(
                  items.elementAt(index),
                  style: const TextStyle(
                    color: Colors.white,
                  ),
                  textAlign: TextAlign.center,
                ),
              ),
            ),
          ),
        ),
      ],
    );
  }
}
