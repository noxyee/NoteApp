SERVICES=(
    "config-server"
    "discovery-server"
    "gateway"
    "note"
    "notification"
    "tag"
    "task"
)

cd services || exit

for service in "${SERVICES[@]}"
do
    echo -e "Building: $service service"

    cd "$service" || { echo -e "Cannot access directory $service"; continue; }

    echo "Removing target folder"
    rm -rf target/

    echo "mvn clean install"
    ./mvnw clean install -DskipTests || {
        echo -e "An error occurred during build of $service"
        cd ..
        continue
    }

    echo -e "$service service was built successfully"

    cd ..
done


