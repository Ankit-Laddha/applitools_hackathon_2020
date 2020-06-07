#!/usr/bin/env bash

for ARGUMENT in "$@"; do

  KEY=$(echo $ARGUMENT | cut -f1 -d=)
  VALUE=$(echo $ARGUMENT | cut -f2 -d=)

  case "$KEY" in
  APP_VERSION | appVersion | VERSION | version)
    APP_VERSION=${VALUE}
    ;;
  TEST_TYPE | test_type | type | tests)
    TEST_TYPE=${VALUE}
    ;;
  *) ;;
  esac
done

if [[ -z "${APP_VERSION}" ]]; then
  echo -e "\nAPP_VERSION is not set, defaulting to version <v1>"
  APP_VERSION="v1"
elif [[ ! "${APP_VERSION}" =~ ^(v1|v2)$ ]]; then
  echo -e "\nAPP_VERSION has to be either <v1> or <v2>"
  echo -e "Not Running tests. Exiting with code 1."
  exit 1
fi

if [[ -z "${TEST_TYPE}" ]]; then
  echo -e "\nTEST_TYPE is not set, Defaulting to <traditional> tests"
  TEST_TYPE="traditional"
elif [[ ! "${TEST_TYPE}" =~ ^(traditional|modern|t)$ ]]; then
  echo -e "\nTEST_TYPE wrongly set. It has to be either <traditional> OR <modern>"
  echo -e "Not Running tests. Exiting with code 1."
  exit 1
fi

echo -e "\nRunning <${TEST_TYPE}> tests against APP version <${APP_VERSION}>"
eval APP_VERSION=${APP_VERSION} mvn clean test -Dgroups=${TEST_TYPE}
