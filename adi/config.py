import os
from dataclasses import dataclass


@dataclass(frozen=True)
class Settings:
    login: str
    password: str
    account: str = "DEMO"
    symbol: str = "SOLANA"
    interval: str = "MN"
    browser: str = "edge"
    timeout: int = 10

    @classmethod
    def from_environment(cls) -> "Settings":
        login = os.getenv("XTB_LOGIN")
        password = os.getenv("XTB_PASSWORD")
        if not login or not password:
            raise RuntimeError("Set XTB_LOGIN and XTB_PASSWORD before running the strategy.")
        return cls(
            login=login,
            password=password,
            account=os.getenv("XTB_ACCOUNT", "DEMO"),
            symbol=os.getenv("XTB_SYMBOL", "SOLANA"),
            interval=os.getenv("XTB_INTERVAL", "MN"),
            browser=os.getenv("XTB_BROWSER", "edge"),
            timeout=int(os.getenv("XTB_TIMEOUT", "10")),
        )
