import 'package:flutter/material.dart';

class DrawerItem extends StatelessWidget {
  final IconData _icon;
  final String title;
  final VoidCallback function;

  const DrawerItem(this.title, this._icon, this.function, {Key? key})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListTile(
      onTap: function,
      leading: Icon(_icon),
      title: Text(title),
    );
  }
}
