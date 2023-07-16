import 'package:flutter/material.dart';

class CategoryModel {
  final String _id;
  final String _title;
  final Color _color;

  const CategoryModel({required String id, required String title, Color color = Colors.pink})
      : _id = id,
        _title = title,
        _color = color;

  Color get color => _color;

  String get title => _title;

  String get id => _id;
}
