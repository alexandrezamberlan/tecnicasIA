class Stack:
    def __init__(self):
        self.items = []

    def push(self, item) -> None:
        self.items.append(item)

    def pop(self) -> any:
        return self.items.pop()

    def is_empty(self) -> bool:
        return len(self.items) == 0

    def __str__(self) -> str:
        return str(self.items)

    def __len__(self) -> int:
        return len(self.items)

    def clone(self) -> "Stack":
        s = Stack()
        s.items = self.items.copy()
        return s
    
    def peek(self) -> any:
        return self.items[-1] if not self.is_empty() else None  
