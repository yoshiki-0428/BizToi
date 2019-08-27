workflow "Build and Lint on push" {
  resolves = [
    "Lint",
    "Deploy",
    "run",
  ]
  on = "push"
}

action "Install" {
  needs = ["run"]
  uses = "docker://node:10"
  args = "yarn install"
}

action "run" {
  uses = "sh"
  runs = "cd front"
}

action "Build" {
  uses = "docker://node:10"
  needs = ["Install"]
  args = "yarn build"
}

action "Lint" {
  uses = "docker://node:10"
  needs = ["Install"]
  args = "yarn lint"
}

action "Only master branch" {
  uses = "actions/bin/filter@707718ee26483624de00bd146e073d915139a3d8"
  needs = ["Build", "Lint"]
  args = "branch master"
}

action "Deploy" {
  uses = "docker://node:10"
  needs = ["Only master branch"]
  args = ["bash", "-c", "yarn firebase deploy --token $FIREBASE_TOKEN"]
  secrets = ["FIREBASE_TOKEN"]
}
