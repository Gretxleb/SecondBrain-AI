# Git Workflow

## Branching

- `main` — production-ready code
- `develop` — integration branch for features
- `feature/*` — new work items

## Commit workflow

1. Create a feature branch:
   - `git checkout -b feature/ai-notes`
2. Stage files:
   - `git add .`
3. Commit with a descriptive message:
   - `git commit -m "Add backend note sync API"`
4. Push branch:
   - `git push origin feature/ai-notes`

## Pull request process

- Open PR against `develop` or `main`
- Include testing instructions and deployment notes
- Run CI workflows before merge

## Release flow

1. Merge `develop` into `main`
2. Tag release:
   - `git tag -a v1.0.0 -m "Release v1.0.0"`
3. Push tags:
   - `git push origin main --tags`
