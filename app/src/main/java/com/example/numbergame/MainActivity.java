package com.example.numbergame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private int attemptCount = 0;
    private final int maxAttempts = 8;  // Максимальное количество попыток
    private EditText userInput;
    private TextView resultTextView;
    private Button guessButton, restartButton;
    private int min = 1;
    private int max = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов интерфейса
        userInput = findViewById(R.id.editTextNumber);
        resultTextView = findViewById(R.id.resultTextView);
        guessButton = findViewById(R.id.guessButton);
        restartButton = findViewById(R.id.restartButton);

        // Генерация случайного числа
        generateRandomNumber();

        // Обработка клика на кнопку "Угадать"
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        // Обработка клика на кнопку "Играть заново"
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }

    // Метод для генерации случайного числа и сброса попыток
    private void generateRandomNumber() {
        randomNumber = (int) (Math.random() * (max - min + 1)) + min;
        attemptCount = 0;  // Сброс количества попыток
        resultTextView.setText("");
        userInput.setText("");
    }

    // Метод для проверки введенного числа
    private void checkGuess() {
        String userInputText = userInput.getText().toString();

        // Проверка типизации и диапазона
        if (userInputText.isEmpty()) {
            showToast("Введите число");
            return;
        }

        try {
            int userGuess = Integer.parseInt(userInputText);

            if (userGuess < min || userGuess > max) {
                showToast("Число должно быть в диапазоне от " + min + " до " + max);
                return;
            }

            // Увеличиваем количество попыток
            attemptCount++;

            // Проверка, угадал ли пользователь число
            if (userGuess > randomNumber) {
                resultTextView.setText("Меньше! Осталось попыток: " + (maxAttempts - attemptCount));
            } else if (userGuess < randomNumber) {
                resultTextView.setText("Больше! Осталось попыток: " + (maxAttempts - attemptCount));
            } else {
                resultTextView.setText("Поздравляем! Вы угадали число за " + attemptCount + " попыток!");
                endGame();  // Игра завершена
                return;
            }

            // Проверка, не закончились ли попытки
            if (attemptCount >= maxAttempts) {
                resultTextView.setText("Вы проиграли! Правильное число было: " + randomNumber);
                endGame();  // Игра завершена
            }

        } catch (NumberFormatException e) {
            showToast("Введите допустимое число!");
        }
    }

    // Метод для завершения игры (показываем кнопку "Играть заново")
    private void endGame() {
        guessButton.setEnabled(false);
        restartButton.setVisibility(View.VISIBLE);  // Показываем кнопку "Играть заново"
    }

    // Метод для перезапуска игры
    private void restartGame() {
        generateRandomNumber();
        guessButton.setEnabled(true);
        restartButton.setVisibility(View.GONE);  // Скрываем кнопку "Играть заново"
        showToast("Игра началась заново! Угадайте число.");
    }

    // Метод для отображения Toast сообщений
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
