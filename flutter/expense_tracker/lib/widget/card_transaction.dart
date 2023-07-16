import '../model/transaction_model.dart';
import 'package:flutter/material.dart';

import 'package:intl/intl.dart';

class TransactionCard extends StatelessWidget {
  final Transaction transaction;
  final Function deleteTransaction;

  const TransactionCard(this.transaction, this.deleteTransaction, {super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 1,
      shadowColor: Theme.of(context).primaryColor,
      margin: const EdgeInsets.fromLTRB(10, 0, 10, 5),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Expanded(
            flex: 30,
            child: Container(
              margin: const EdgeInsets.all(10),
              padding: const EdgeInsets.all(5),
              height: 50,
              alignment: Alignment.center,
              decoration: BoxDecoration(
                  border: Border.all(
                    color: Theme.of(context).primaryColorDark,
                    width: 1,
                  ),
                  borderRadius: BorderRadius.circular(4)),
              child: FittedBox(
                child: Text(
                  '\u{20B9}${transaction.price.toStringAsFixed(2)}',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    color: Theme.of(context).primaryColor,
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ),
          ),
          Expanded(
            flex: 55,
            child: Container(
              margin: const EdgeInsets.fromLTRB(5, 5, 5, 5),
              padding: const EdgeInsets.fromLTRB(0, 5, 5, 5),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  FittedBox(
                    child: Text(
                      transaction.title,
                      style: Theme.of(context).textTheme.titleLarge,
                    ),
                  ),
                  Text(
                    DateFormat.yMMMd().format(transaction.date),
                    // '${transaction.date.day}-${transaction.date.month}-${transaction.date.year}',
                    style: const TextStyle(
                      color: Colors.black26,
                      fontSize: 16,
                    ),
                  ),
                ],
              ),
            ),
          ),
          Flexible(
            fit: FlexFit.tight,
            flex: 15,
            child: Container(
              // padding: const EdgeInsets.all(5),
              margin: const EdgeInsets.fromLTRB(0, 10, 10, 10),
              child: FittedBox(
                child: IconButton(
                  color: const Color.fromRGBO(51, 51, 51, 1.0),
                  icon: const Icon(
                    Icons.delete_forever,
                  ),
                  onPressed: () => deleteTransaction(transaction.id),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
