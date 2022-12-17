import 'package:flutter/material.dart';

class LabelCard extends StatelessWidget {
  final String _label;
  final bool _selected;

  const LabelCard(this._label, this._selected, {super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        margin: const EdgeInsets.only(right: 10),
        padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 15),
        decoration: BoxDecoration(
          color: _selected ? const Color.fromRGBO(74, 128, 240, 1.0) : const Color.fromRGBO(28, 32, 49, 1.0),
          borderRadius: const BorderRadius.all(Radius.circular(10)),
        ),
        child: Text(
          _label,
          style: const TextStyle(
            color: Colors.white,
            fontFamily: 'sf',
            fontSize: 18,
            fontWeight: FontWeight.w300,
          ),
          textAlign: TextAlign.center,
        ),
      ),
    );
  }
}
