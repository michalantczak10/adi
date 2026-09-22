# adi

Automatyzacja Selenium dla XTB przepisana z Javy na Pythona.

## Uruchomienie

1. Utwórz środowisko i zainstaluj zależności:

   ```powershell
   py -m venv .venv
   .\.venv\Scripts\Activate.ps1
   pip install -e ".[test]"
   ```

2. Ustaw dane logowania w zmiennych środowiskowych. Skopiuj `.env.example` i użyj wartości właściwych dla swojego konta:

   ```powershell
   $env:XTB_LOGIN = "twoj-login"
   $env:XTB_PASSWORD = "twoje-haslo"
   $env:XTB_ACCOUNT = "DEMO"
   ```

3. Uruchom test integracyjny świadomie:

   ```powershell
   $env:RUN_XTB_TEST = "1"
   pytest
   ```

Test wykonuje rzeczywiste operacje w interfejsie XTB. Do pierwszych prób używaj konta DEMO.
