import 'package:flutter/material.dart';

class InputField extends StatefulWidget {
  final String label;
  final String hint;
  final TextEditingController controller;
  final TextInputType type;
  final IconData prefixIcon;
  final List<IconData>? suffixIcons;

  const InputField({
    required this.label,
    required this.hint,
    required this.controller,
    required this.type,
    required this.prefixIcon,
    this.suffixIcons,
    super.key,
  });

  @override
  State<InputField> createState() => _InputFieldState();
}

class _InputFieldState extends State<InputField> {
  bool _obscure = false;
  bool _isPassword = false;
  int _suffixIndex = 0;

  @override
  void initState() {
    super.initState();
    if (widget.type == TextInputType.visiblePassword) {
      _obscure = true;
      _isPassword = true;
    }
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 350,
      height: 60,
      child: TextField(
        controller: widget.controller,
        keyboardType: widget.type,
        obscureText: _obscure,
        enableSuggestions: false,
        autocorrect: false,
        textAlign: TextAlign.start,
        decoration: InputDecoration(
          labelText: widget.label,
          hintText: widget.hint,
          prefixIcon: Padding(
            padding: const EdgeInsets.only(left: 20, right: 10),
            child: Icon(
              widget.prefixIcon,
              size: 20,
            ),
          ),
          suffixIcon: _isPassword
              ? Padding(
                  padding: const EdgeInsets.only(left: 10, right: 10),
                  child: IconButton(
                    onPressed: () {
                      setState(() {
                        _suffixIndex = (_suffixIndex + 1) % 2;
                        _obscure = !_obscure;
                      });
                    },
                    icon: Icon(
                      widget.suffixIcons!.elementAt(_suffixIndex),
                      size: 20,
                    ),
                  ),
                )
              : null,
          border: const OutlineInputBorder(
            borderRadius: BorderRadius.all(
              Radius.circular(50),
            ),
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(50),
            borderSide: const BorderSide(color: Color.fromARGB(255, 159, 202, 255)),
          ),
          floatingLabelStyle: const TextStyle(
            color: Color.fromARGB(255, 159, 202, 255),
            fontWeight: FontWeight.w600,
          ),
        ),
      ),
    );
  }
}
