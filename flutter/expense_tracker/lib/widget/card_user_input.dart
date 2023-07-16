import 'package:expense_tracker/model/transaction_model.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class UserInputCard extends StatefulWidget {
  final Function addTransaction;

  const UserInputCard(this.addTransaction, {super.key});

  @override
  State<UserInputCard> createState() => _UserInputCardState();
}

class _UserInputCardState extends State<UserInputCard> {
  final titleController = TextEditingController();
  final priceController = TextEditingController();

  DateTime _dateTime = DateTime.now();

  void _showDatePicker() {
    showDatePicker(
            context: context,
            initialDate: DateTime.now(),
            firstDate: DateTime(2001),
            lastDate: DateTime.now())
        .then((value) {
      if (value == null) {
        return;
      }
      changeTheDate(value);
    });
  }

  void changeTheDate(DateTime value) {
    setState(() {
      _dateTime = value;
    });
    debugPrint(DateFormat().add_yMMMd().format(_dateTime));
  }

  void submitData() {
    debugPrint('title: ${titleController.text}');
    debugPrint('price: ${priceController.text}');

    if (priceController.text.isEmpty || titleController.text.isEmpty) {
      return;
    }
    widget.addTransaction(Transaction(
        '${titleController.text}@${priceController.text}on$_dateTime',
        double.parse(priceController.text),
        titleController.text,
        _dateTime));

    Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Card(
        elevation: 5,
        child: Container(
          padding: EdgeInsets.only(
              left: 10,
              top: 10,
              right: 10,
              bottom: MediaQuery.of(context).viewInsets.bottom + 10),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              TextField(
                decoration: const InputDecoration(labelText: 'Title'),
                controller: titleController,
                onSubmitted: (_) => submitData(),
              ),
              TextField(
                decoration: const InputDecoration(labelText: 'Amount'),
                controller: priceController,
                keyboardType: TextInputType.number,
                onSubmitted: (_) => submitData(),
              ),
              SizedBox(
                height: 70,
                child: Row(
                  children: [
                    Expanded(
                      child: Text(
                        'Picked Date: ${DateFormat.yMMMd().format(_dateTime)}',
                      ),
                    ),
                    TextButton(
                      style: TextButton.styleFrom(
                          foregroundColor: Theme.of(context).primaryColor),
                      onPressed: _showDatePicker,
                      child: const Text(
                        'Change',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(
                height: 10,
              ),
              ElevatedButton(
                onPressed: submitData,
                child: const Text("Add Transaction"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
