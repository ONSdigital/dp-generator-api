job "dp-generator-api" {
  datacenters = ["DATA_CENTER"]
  constraint {
  }
  update {
    stagger = "10s"
    max_parallel = 1
  }
  group "dp-generator-api" {
    task "dp-generator-api" {
      artifact {
        source = "s3::S3_TAR_FILE"
      }
      env {
        DB_ACCESS = "DB_ACCESS_URL"
        DB_USER = "USER"
        DB_PW = "PASSWORD"
        PORT = "GENERATOR_PORT"
        HUMAN_LOG = "HUMAN_LOG_FLAG"
      }
      driver = "java"
      config {
        jar_path = "local/dp-generator-api-0.1.0.jar"
        jvm_options = ["-Xmx512m", "-Xms256m"]
      }
      resources {
        cpu = 600
        memory = 600
        network {
          port "http" {
            static = "GENERATOR_PORT"
          }
        }
      }
      service {
        port = "${NOMAD_PORT_http}"
        check {
          type = "http"
          path = "/health"
          interval = "10s"
          timeout = "2s"
>>>>>>> HTTP Health check
        }

      }
    }
  }
}
