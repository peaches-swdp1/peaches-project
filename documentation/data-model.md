# Entity Relationship Diagram 
```mermaid
erDiagram
    ReadingRecommendation {
        long id PK
        string title
        string link
        string description
        long categoryId FK
        long userId FK
        instant createdOn
        instant lastUpdatedOn
    }
    AppUser ||--o{ ReadingRecommendation : creates
    AppUser {
        long userId PK
        string username
        string passwordHash
        string role
    }
    Category ||--o{ ReadingRecommendation : contains
    Category {
        long categoryId PK
        string name
    }
```
